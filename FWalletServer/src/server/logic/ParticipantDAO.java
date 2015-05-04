package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.entity.AuthSession;
import server.entity.Participant;

public class ParticipantDAO {
    public static void giveMoney(Session session, String token, long groupId, long recipientId, float amount) throws Exception{
        if (amount<0) throw new IllegalArgumentException("Amount must be positive!");
        try {
            session.getTransaction().begin();
            AuthSession auth = AuthSessionDAO.getSessionByToken(session, token);
            Participant sender = (Participant) session.createCriteria(Participant.class)
                .add(Restrictions.eq("groupId", groupId))
                .add(Restrictions.eq("participantId", auth.getUserid()))
                .uniqueResult();
            System.out.println(sender);
            Participant recipient = (Participant) session.createCriteria(Participant.class)
                .add(Restrictions.eq("groupId", groupId))
                .add(Restrictions.eq("participantId", recipientId))
                .uniqueResult();
            if (sender==null || recipient==null) throw new IllegalAccessException("Sender and recipient must be in same group!");
            sender.setBalance(sender.getBalance()+amount);
            recipient.setBalance(recipient.getBalance()-amount);
            session.saveOrUpdate(sender);
            session.saveOrUpdate(recipient);
            session.getTransaction().commit();
        } catch (Throwable e){
            session.getTransaction().rollback();
            throw e;
        }
    }
}
