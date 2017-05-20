package ir.rendan.dao;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.Comment;
import ir.rendan.model.Team;
import org.hibernate.Criteria;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ABM on 5/18/2017.
 */


public class CommentDAO {
    public List getAllComment(){
        Criteria cr = HibernateUtils.getSession().createCriteria(Comment.class);
        List<Comment> results = cr.list();

        return results;
    }
}
