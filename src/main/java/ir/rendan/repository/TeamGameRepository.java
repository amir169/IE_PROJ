package ir.rendan.repository;

import ir.rendan.model.TeamGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Amir Shams on 6/24/2017.
 */
public interface TeamGameRepository extends CrudRepository<TeamGame,Integer> {

    @Query("select tg from TeamGame tg join tg.game g join tg.team t join tg.team.members mem " +
            "where g.id = ?2 and mem.username = ?1 and t.validated = 1")
    List<TeamGame> isRegistered(String username, int gameId);

}
