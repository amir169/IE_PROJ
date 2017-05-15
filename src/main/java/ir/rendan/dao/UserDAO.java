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
@Transactional
public class UserDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	public UserInfo getActiveUser(String userName) {
		return getByUserName(userName);
		/*
		UserInfo activeUserInfo = new UserInfo();
		Criteria c = HibernateUtils.getSession().createCriteria(UserInfo.class);
		c.add(Restrictions.eq("username",userName));
		List<?> list = c.list();
		if(!list.isEmpty()) {
			activeUserInfo = (UserInfo)list.get(0);
		}
		return activeUserInfo;
*/
	}

	public void insert(UserInfo u){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        String out =  session.save(u).toString();
        tx.commit();
    }

    public UserInfo getByUserName(String name){
        return HibernateUtils.getSession().get(UserInfo.class,name);
    }

}