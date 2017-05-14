package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.TeamInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by SalehJFZ on 14/05/2017.
 */

public class TeamDAO {
    public TeamInfo getByName(String name){
        return HibernateUtils.getSession().get(TeamInfo.class,name);
    }

    public void insert(TeamInfo team)
    {
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.save(team);
        tx.commit();
    }

    public void deleet(TeamInfo team){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.delete(team);
        tx.commit();

    }

    public void update(TeamInfo team){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.update(team);
        tx.commit();}
}
