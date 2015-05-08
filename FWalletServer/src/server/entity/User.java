package server.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.json.simple.JSONObject;
import server.io.JSONAble;
import utils.NetworkUtils;

@Entity
@Table(name="USERS")
public class User implements JSONAble{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="PASSWORD")
    private String passwd;
    
    @Column(name="EMAIL")
    private String mail;
    
    @Column(name="REGISTRATION")
    private Timestamp registration;
    
    @Column(name="LASTENTER")
    private Timestamp lastEnter;
    
    @OneToMany
    @JoinTable(name="PARTICIPANTS",joinColumns = @JoinColumn(name = "PARTICIPANTID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    private List<Participant> groups;
    
    @OneToMany
    @JoinTable(name="INVITATIONS",joinColumns = @JoinColumn(name = "RECIPIENTID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    private List<Invitation> invitations;

    public List<Invitation> getInvitations() {
        return invitations;
    }
       
    public List<Participant> getGroups(){
        return groups;
    }
    
    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setPasswd(String password) {
        this.passwd = NetworkUtils.toHexMd5(password);
        return this;
    }

    public User setEmail(String mail) {
        this.mail = mail;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswdMd5() {
        return passwd;
    }

    public String getMail() {
        return mail;
    }

    public Timestamp getRegistration() {
        return registration;
    }

    public Timestamp getLastEnter() {
        return lastEnter;
    }
    
    public String getAvatar(){
        return NetworkUtils.getGravatarURL(mail);
    }

    
    
    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("name", getName());
        json.put("mail", getMail());
        json.put("avatar", getAvatar());
        return json;
    }
    
    
}
