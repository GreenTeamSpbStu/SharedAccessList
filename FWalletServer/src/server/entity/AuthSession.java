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
@Table(name="SESSIONS")
public class AuthSession implements JSONAble{
    
    @Column(name="TOKEN")
    private String token;
    
    @Id
    @Column(name="USERID")
    private Long  userid;
    
    @OneToOne
    @JoinColumn(name = "USERID")
    private User user;

    public Long getUserid() {
        return userid;
    }

    public User getUser() {
        return user;
    }
    
    public AuthSession setToken(String token) {
        this.token = token;
        return this;
    }

    public AuthSession setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("token", token);
        return json;    
    }
    
}
