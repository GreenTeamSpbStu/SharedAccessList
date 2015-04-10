package server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author magni
 */
public class Community {
    protected String groupName;
    protected Person groupAdmin;
    protected Date groupCreationDate;
    protected String groupAnnotation;
    protected List<Person> groupMembers;
    // protected List<> groupFeed;
    // protected List<> groupOrders
    // protected List<> groupPurchases;

    public Community(String groupName, Person groupAdmin, Date groupCreationDate, String groupAnnotation, List<Person> groupMembers) {
        this.groupName = groupName;
        this.groupAdmin = groupAdmin;
        this.groupCreationDate = groupCreationDate;
        this.groupAnnotation = groupAnnotation;
        this.groupMembers = groupMembers;
    }

    public Community(Person admin) {
        this.groupName = "";
        this.groupAdmin = admin;
        this.groupCreationDate = new Date();
        this.groupAnnotation = "";
        this.groupMembers = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public Date getGroupCreationDate() {
        return groupCreationDate;
    }

    public void setGroupCreationDate(Date groupCreationDate) {
        this.groupCreationDate = groupCreationDate;
    }

    public String getGroupAnnotation() {
        return groupAnnotation;
    }

    public void setGroupAnnotation(String groupAnnotation) {
        this.groupAnnotation = groupAnnotation;
    }

    public List<Person> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<Person> groupMembers) {
        this.groupMembers = groupMembers;
    }
    
    public void addPerson(Person person){
        this.groupMembers.add(person);
    }  
}
