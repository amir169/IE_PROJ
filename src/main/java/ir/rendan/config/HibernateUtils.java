package ir.rendan.config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Created by Amir Shams on 5/2/2017.
 */
public class HibernateUtils {

    private static Session session;
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory getSessionFactory() {
        if(sessionFactory !=null && sessionFactory.isOpen())
            return sessionFactory;
        sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static Session getSession()
    {
        if(session != null && session.isOpen())
            return session;

        session = getSessionFactory().openSession();

        return session;
    }

}