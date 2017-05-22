package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class UserDAO extends GenericDAO {

	public UserInfo getActiveUser(String userName) {

		UserInfo result = getByUserName(userName);

		if(result != null && result.getEnabled() == 1)
			return result;

		return null;
	}

    public UserInfo getByUserName(String name){
        return HibernateUtils.getSession().get(UserInfo.class,name);
    }


	public UserInfo getByActivationCode(String code) {
		CriteriaBuilder builder = HibernateUtils.getSession().getCriteriaBuilder();
		CriteriaQuery<UserInfo> cq = builder.createQuery(UserInfo.class);
		Root<UserInfo> root = cq.from(UserInfo.class);
		cq.where(builder.equal(root.get("activationCode"),code));

		return HibernateUtils.getSession().createQuery(cq).getSingleResult();

	}
}