package server.logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import server.HibernateUtil;
import server.entity.AuthSession;
import server.entity.Invitation;
import server.entity.Participant;

public class InvitationDAO {
    public static void acceptInvitation(String token, long invitationID, boolean reject) throws IllegalAccessException, IllegalArgumentException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Invitation invitation = null;
        try {
            AuthSession auth = AuthSessionDAO.getSessionByToken(token);
            invitation = (Invitation) session.createCriteria(Invitation.class)
                .add(Restrictions.eq("id", invitationID))
                .add(Restrictions.eq("recipientId",auth.getUserid()))
                .uniqueResult();
            if (invitation==null) throw new IllegalAccessException("There are no such invitation!");
            session.getTransaction().begin();
            session.delete(invitation);
            if (!reject) {
                Participant participant = new Participant()
                    .setBalance(0)
                    .setGroupId(invitation.getGroupId())
                    .setParticipantId(auth.getUserid());
                session.save(participant);
            }
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            rejectInvitation(invitation);
            throw new IllegalArgumentException("User already participates this group!");
        } finally {
            session.close();
        }
    }
    
    private static void rejectInvitation(Invitation invitation){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(invitation);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    public static void createInvitation(String token, long groupId, long userId) throws IllegalAccessException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            AuthSession auth = AuthSessionDAO.getSessionByToken(token);
            Participant participant = (Participant) session.createCriteria(Participant.class)
                .add(Restrictions.eq("groupId", groupId))
                .add(Restrictions.eq("participantId", auth.getUserid()))
                .uniqueResult();
            if (participant==null) throw new IllegalAccessException("You must be in group to invite!");
            session.getTransaction().begin();
            Invitation invitation = new Invitation()
                    .setGroupId(groupId)
                    .setRecipientId(userId)
                    .setSenderId(auth.getUserid());
            session.save(invitation);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
