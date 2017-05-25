package ir.rendan.repository;

import ir.rendan.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Amir Shams on 5/25/2017.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>
{
    @Query("select u from UserInfo u where u.activationCode = ?1")
    UserInfo findByActivationCode(String activationCode);

    @Query("select u from UserInfo u where u.username = ?1 and u.enabled = 1")
    UserInfo getActiveUser(String username);
}


