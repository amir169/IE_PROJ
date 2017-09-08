package ir.rendan.services;

import ir.rendan.model.Code;
import ir.rendan.model.DataFile;
import ir.rendan.model.TeamGame;
import ir.rendan.repository.CodeRepository;
import ir.rendan.repository.DataFileRepository;
import ir.rendan.repository.TeamGameRepository;
import ir.rendan.services.dto.UploadCodeDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Amir Shams on 8/2/2017.
 */
@Path("api/code")
@Service
public class CodeService {

    private final TeamGameRepository teamGameRepository;
    private final CodeRepository codeRepository;
    private final DataFileRepository dataFileRepository;

    public CodeService(TeamGameRepository teamGameRepository, CodeRepository codeRepository, DataFileRepository dataFileRepository) {
        this.teamGameRepository = teamGameRepository;
        this.codeRepository = codeRepository;
        this.dataFileRepository = dataFileRepository;
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response upload(UploadCodeDTO dto)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        DataFile dataFile = dataFileRepository.getByKey(dto.getCode());

        if(dataFile == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Code code = new Code(dataFile.getAddress());

        codeRepository.save(code);
        code.setAddress(dto.getCode());

        List<TeamGame> teamGameList = teamGameRepository.isRegistered(username,dto.getGame());

        if(teamGameList.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        TeamGame teamGame = teamGameList.get(0);

        teamGame.addToCodes(code);

        teamGameRepository.save(teamGame);

        return Response.ok().build();
    }
}
