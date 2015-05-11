package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.User;

public class UserDAO {
    public static User getUser(long id) throws IllegalAccessException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            User user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            if (user==null) throw new IllegalAccessException("There are no user with this id!");
            return user;
        } finally {
            session.close();
        }
    }
    
    public static void register(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
}
