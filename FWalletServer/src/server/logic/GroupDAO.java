package server.logic;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Group;
import server.entity.User;

/**
 *
 * @author magni
 */
public class GroupDAO {
    public static void createGroup(Group gr){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            session.save(gr);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
    
    public static Group getGroup(long id) throws IllegalAccessException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
                Group group = (Group) session.createCriteria(Group.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
                if (group==null) throw new IllegalAccessException("There are no these group!");
                return group;
        } finally {
            session.close();
        }
    }
}
