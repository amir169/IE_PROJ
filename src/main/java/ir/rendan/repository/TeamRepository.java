package ir.rendan.repository;

import ir.rendan.model.Team;
import ir.rendan.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by SalehJFZ on 13/06/2017.
 */
public interface TeamRepository extends CrudRepository<Team,String> {
    @Query("select t from Team t join t.members user " +
            "where user.username = ?1 and (t.validated = 1 or user = t.manager)")
    List<Team> findByUser(String username);
}
