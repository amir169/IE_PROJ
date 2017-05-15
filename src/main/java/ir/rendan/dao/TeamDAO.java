package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by SalehJFZ on 14/05/2017.
 */

public class TeamDAO extends GenericDAO{
    public Team getByName(String name){
        return HibernateUtils.getSession().get(Team.class,name);
    }

}
