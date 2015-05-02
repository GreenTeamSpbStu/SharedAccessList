package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.entity.Participant;

public class ParticipantDAO {
    public static List<Participant> getGroups(Session session, long participantId){
        return (List<Participant>) session.createCriteria(Participant.class)
                .add(Restrictions.eq("participantId", participantId))
                .list();
    }
}
