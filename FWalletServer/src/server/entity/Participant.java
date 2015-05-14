package server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.json.simple.JSONObject;
import server.io.JSONAble;

@Entity
@Table(name="PARTICIPANTS")
public class Participant implements JSONAble{
    
    @Id
    @Column(name = "ID")
    private long id;
    
    @Column(name = "GROUPID")
    private long groupId;
    
    @OneToOne
    @JoinColumn(name = "GROUPID",insertable = false, updatable = false)
    private Group group;
    
    @Column(name = "PARTICIPANTID")
    private long participantId;
    
    @OneToOne
    @JoinColumn(name = "PARTICIPANTID",insertable = false, updatable = false)
    private User participant;
    
    @Column(name = "BALANCE")
    private float balance;    

    public Group getGroup() {
        return group;
    }

    public long getGroupId() {
        return groupId;
    }
    
    public User getParticipant(){
        return participant;
    }

    public long getParticipantId() {
        return participantId;
    }

    public float getBalance() {
        return balance;
    }

    public Participant setGroupId(long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Participant setParticipantId(long participantId) {
        this.participantId = participantId;
        return this;
    }

    public Participant setBalance(float balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("user", getParticipant().asJSON());
        json.put("balance", getBalance());
        return json;
    }

}
