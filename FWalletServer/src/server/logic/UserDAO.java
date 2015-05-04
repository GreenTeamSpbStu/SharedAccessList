package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.entity.User;

public class UserDAO {
    public static User getUser(Session session, long id) throws IllegalAccessException{
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        if (user==null) throw new IllegalAccessException("There are no user with this id!");
        return user;
    }
    
    public static void register(Session session, User user){
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
    
}
