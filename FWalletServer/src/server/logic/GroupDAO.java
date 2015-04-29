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
    
    public static List<User> getMembersOfGroup(Long groupId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<User> groupMembers = new ArrayList<>();
        
        try {
        } finally {
            session.close();
        }
        
        return groupMembers;
    }
}
