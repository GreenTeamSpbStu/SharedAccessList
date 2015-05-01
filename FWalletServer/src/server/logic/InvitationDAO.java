package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Invitation;

public class InvitationDAO {
    public static List<Invitation> getInvitations(long recipientID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Invitation>) session.createCriteria(Invitation.class)
                    .add(Restrictions.eq("recipientId", recipientID))
                    .list();
        } finally {
            session.close();
        }
    }
}
