package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.entity.Group;

public class GroupDAO {
    public static void createGroup(Session session, Group gr){
        try {
            session.beginTransaction();
            session.save(gr);
        } finally {
            session.getTransaction().commit();
        }
    }
    
    public static Group getGroup(Session session, long id) throws IllegalAccessException {
        Group group = (Group) session.createCriteria(Group.class)
            .add(Restrictions.eq("id", id))
            .uniqueResult();
        if (group==null) throw new IllegalAccessException("There are no these group!");
        return group;
    }
}
