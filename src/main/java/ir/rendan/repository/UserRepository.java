package ir.rendan.repository;

import ir.rendan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Amir Shams on 5/25/2017.
 */
public interface UserRepository extends CrudRepository<User, String>
{
    @Query("select u from User u where u.activationCode = ?1")
    User findByActivationCode(String activationCode);

    @Query("select u from User u where u.username = ?1 and u.enabled = 1")
    User getActiveUser(String username);
}


