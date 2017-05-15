package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
public class GenericDAO {

    public Object getByName(String name){
        throw new NotImplementedException();
    }


    public void insert(Object team)
    {
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.save(team);
        tx.commit();
    }

    public void deleet(Object team){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.delete(team);
        tx.commit();

    }

    public void update(Object team){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.update(team);
        tx.commit();}
}
