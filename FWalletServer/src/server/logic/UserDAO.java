package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.AuthSession;
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
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
    
    public static User getUserByToken(String token) throws IllegalAccessException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        try {
            AuthSession auth = (AuthSession) session.createCriteria(AuthSession.class)
                    .add(Restrictions.eq("token", token))
                    .uniqueResult();
            if (auth==null) throw new IllegalAccessException("Wrong token!");
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("id", auth.getUserid()))
                    .uniqueResult();
        } finally {
            session.close();
        }
        return user;
    }
    
    
}
