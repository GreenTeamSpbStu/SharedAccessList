package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.AuthSession;
import server.entity.User;
import utils.NetworkUtils;

public class AuthSessionDAO {
    public static AuthSession authorize(String mail, String passwd) throws IllegalAccessException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            passwd = NetworkUtils.toHexMd5(passwd);
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("mail", mail))
                .add(Restrictions.eq("passwd", passwd))
                .uniqueResult();
            if (user==null) throw new IllegalAccessException("Wrong password or login!");
            AuthSession auth = new AuthSession()
                .setUserid(user.getId())
                .setToken(createToken(mail, passwd));
            session.saveOrUpdate(auth);
            session.getTransaction().commit();
            return auth;
        } finally {
            session.close();
        }
    }
    
    private static String createToken(String name, String passwd){
        return NetworkUtils.toHexMd5(name+passwd+System.currentTimeMillis());
    }
     
    public static AuthSession getSessionByToken(String token) throws IllegalAccessException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            AuthSession auth = (AuthSession) session.createCriteria(AuthSession.class)
                    .add(Restrictions.eq("token", token))
                    .uniqueResult();
            if (auth==null) throw new IllegalAccessException("Wrong token!");
            return auth;
        } finally {
            session.close();
        }
    }
}
