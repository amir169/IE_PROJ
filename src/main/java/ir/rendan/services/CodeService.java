package ir.rendan.services;

import ir.rendan.model.Code;
import ir.rendan.model.DataFile;
import ir.rendan.model.TeamGame;
import ir.rendan.repository.CodeRepository;
import ir.rendan.repository.DataFileRepository;
import ir.rendan.repository.TeamGameRepository;
import ir.rendan.services.dto.SelectCodeDTO;
import ir.rendan.services.dto.UploadCodeDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

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

    @GET
    @Path("get-all/{game_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAllMyCodes(@PathParam("game_id") int id)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<TeamGame> teamGameList = teamGameRepository.isRegistered(username,id);

        if(teamGameList.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        TeamGame teamGame = teamGameList.get(0);

        teamGame.getCodes().size();
        Set<Code> codes = teamGame.getCodes();

        return Response.ok(codes).build();
    }

    @POST
    @Path("select-code")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response selectCode(SelectCodeDTO dto)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Code code = codeRepository.findOne(dto.getCode());

        List<TeamGame> teamGameList = teamGameRepository.isRegistered(username,dto.getGame());

        if(teamGameList.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        TeamGame teamGame = teamGameList.get(0);

        teamGame.setSelectedCode(code);

        teamGameRepository.save(teamGame);

        return Response.ok().build();
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
