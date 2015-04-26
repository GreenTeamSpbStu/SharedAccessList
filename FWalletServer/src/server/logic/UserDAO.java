package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.User;

public class UserDAO {
    public static User getUser(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        try {
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } finally {
            session.close();
        }
        return user;
    }
    
    public static void register(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
