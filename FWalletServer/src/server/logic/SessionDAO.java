package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.AuthSession;
import server.entity.User;
import utils.NetworkUtils;

public class SessionDAO {
    public static AuthSession authorize(String mail, String passwd) throws IllegalAccessException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        passwd = NetworkUtils.toHexMd5(passwd);
        User user = null;
        try {
            session.beginTransaction();
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("mail", mail))
                    .add(Restrictions.eq("passwd", passwd))
                    .uniqueResult();
            if (user==null) throw new IllegalAccessException("Wrong password or login!");
            AuthSession auth = new AuthSession()
                    .setUserid(user.getId())
                    .setToken(createToken(mail, passwd));
            session.saveOrUpdate(auth);
            return auth;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
    
    private static String createToken(String name, String passwd){
        return NetworkUtils.toHexMd5(name+passwd+System.currentTimeMillis());
    }
}
