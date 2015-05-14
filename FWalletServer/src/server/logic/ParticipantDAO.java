package server.logic;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.AuthSession;
import server.entity.Participant;

public class ParticipantDAO {
    public static void giveMoney(String token, long groupId, long recipientId, float amount) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (amount<0) throw new IllegalArgumentException("Amount must be positive!");
        try {
            session.getTransaction().begin();
            AuthSession auth = AuthSessionDAO.getSessionByToken(token);
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
        } finally {
            session.close();
        }
    }
    
    public static void join(Participant p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(p);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    public static void leave(long groupId, long userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            
            Participant leaves = (Participant) session.createCriteria(Participant.class)
                .add(Restrictions.eq("groupId", groupId))
                .add(Restrictions.eq("participantId", userId))
                .uniqueResult();
            
            session.delete(leaves);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    public static List<Participant> getAll(long groupId){
        List<Participant> result = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            List all = session.createCriteria(Participant.class)
                .add(Restrictions.eq("groupId", groupId)).list();
            
            for (Object i : all) {
                result.add((Participant)i);
            }
            
            return result;
        } finally {
            session.close();
        }
    }
}
