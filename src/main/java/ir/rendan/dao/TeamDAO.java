package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by SalehJFZ on 14/05/2017.
 */

public class TeamDAO {
    public Team getByName(String name){
        return HibernateUtils.getSession().get(Team.class,name);
    }

    public void insert(Team team)
    {
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.save(team);
        tx.commit();
    }

    public void deleet(Team team){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.delete(team);
        tx.commit();

    }

    public void update(Team team){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.update(team);
        tx.commit();}
}
