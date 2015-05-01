package server.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public long getSenderId() {
        return senderId;
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
        json.put("senderId", getSenderId());
        json.put("groupId", getGroupId());
        json.put("invitationTime", getInvitationTime());
        return json;
    }
    
}
