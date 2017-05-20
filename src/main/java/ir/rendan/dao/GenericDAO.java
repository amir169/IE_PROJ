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


    public void insert( Object ob)
    {
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.save(ob);
        tx.commit();
    }

    public void delete(Object ob){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.delete(ob);
        tx.commit();

    }

    public void update(Object ob){
        Session se = HibernateUtils.getSession();
        Transaction tx = se.beginTransaction();
        se.update(ob);
        tx.commit();}
}
