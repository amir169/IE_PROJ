package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO extends GenericDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	public UserInfo getActiveUser(String userName) {
		return getByUserName(userName);
	}

    public UserInfo getByUserName(String name){
        return HibernateUtils.getSession().get(UserInfo.class,name);
    }

}