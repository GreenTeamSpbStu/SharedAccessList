package server.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.json.simple.JSONObject;
import server.io.JSONAble;

@Entity
@Table(name="INVITATIONS")
public class Invitation implements JSONAble{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    
    @Column(name="SENDERID")
    private long senderId;
    
    @Column(name="RECIPIENTID")
    private long recipientId;
    
    @Column(name="GROUPID")
    private long groupId;
    
    @Column(name="INVITATIONTIME")
    private Timestamp invitationTime;
    
    @OneToOne
    @JoinColumn(name = "SENDERID", insertable = false, updatable = false)
    private User sender;
    
    @OneToOne
    @JoinColumn(name = "GROUPID", insertable = false, updatable = false)
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }
     
    public User getSender() {
        return sender;
    }
   
    public long getRecipientId() {
        return recipientId;
    }

    public long getGroupId() {
        return groupId;
    }

    public Timestamp getInvitationTime() {
        return invitationTime;
    }

    public Invitation setSenderId(long senderId) {
        this.senderId = senderId;
        return this;
    }

    public Invitation setRecipientId(long recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public Invitation setGroupId(long groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("invitationId", id);
        json.put("sender", getSender().asJSON());
        json.put("group", getGroup().asJSON());
        json.put("invitationTime", getInvitationTime().toString());
        return json;
    }
    
}
