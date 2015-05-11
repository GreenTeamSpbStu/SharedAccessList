package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Group;

public class GroupDAO {
    public static void createGroup(Group gr){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(gr);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    public static Group getGroup(long id) throws IllegalAccessException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Group group = (Group) session.createCriteria(Group.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
            if (group==null) throw new IllegalAccessException("There is no such group!");
            return group;
        } finally {
            session.close();
        }
    }
    
    public static void updateGroupInfo(Group gr){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(gr);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
