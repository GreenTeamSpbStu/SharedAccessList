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
@Table(name="GROUPS")
public class Group implements JSONAble{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @Column(name="OWNERID")
    private Long ownerId;
    
    @Column(name="CREATIONDATE")
    private Timestamp creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Group setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Group setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public Group setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject jo = new JSONObject();
        jo.put("id", getId());
        jo.put("name", getName());
        jo.put("description", getDescription());
        jo.put("ownerId", getOwnerId());
        jo.put("creationDate", getCreationDate());
        
        return jo;
    }
}