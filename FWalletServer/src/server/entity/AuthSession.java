package server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    public AuthSession setToken(String token) {
        this.token = token;
        return this;
    }

    public AuthSession setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public Long getUserid() {
        return userid;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("token", token);
        return json;    
    }
    
    
    
}
