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
    protected List<Message> groupFeed;
    protected List<Order> groupOrders;
    protected List<Purchase> groupPurchases;

    public Community(String groupName, Person groupAdmin, String groupAnnotation, List<Person> groupMembers) {
        this.groupName = groupName;
        this.groupAdmin = groupAdmin;
        this.groupCreationDate = new Date();
        this.groupAnnotation = groupAnnotation;
        this.groupMembers = groupMembers;
    }

    public Community(Person admin) {
        this.groupName = "";
        this.groupAdmin = admin;
        this.groupCreationDate = new Date();
        this.groupAnnotation = "";
        this.groupMembers = new ArrayList<>();
        this.groupFeed = new ArrayList<>();
    }

    public List<Purchase> getGroupPurchases() {
        return groupPurchases;
    }

    public void setGroupPurchases(List<Purchase> groupPurchases) {
        this.groupPurchases = groupPurchases;
    }

    public List<Order> getGroupOrders() {
        return groupOrders;
    }

    public void setGroupOrders(List<Order> groupOrders) {
        this.groupOrders = groupOrders;
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
    
    public List<Message> getGroupFeed() {
        return groupFeed;
    }

    public void setGroupFeed(List<Message> groupFeed) {
        this.groupFeed = groupFeed;
    }
    
    public void addMember(Person person){
        this.groupMembers.add(person);
    }  
    
    public void removeMember(Person p){
        this.groupMembers.remove(p);
    }
    
    public void addFeedMessage(Message m){
        this.groupFeed.add(m);
    }
    
    public void removeFeedMessage(Message m){
        this.groupFeed.remove(m);
    }
    
    public void addGroupOrder(Order o){
        this.groupOrders.add(o);
    }
    
    public void removeGroupOrder(Order o){
        this.groupOrders.remove(o);
    }
    
    public void addGroupPurchase(Purchase p){
        this.groupPurchases.add(p);
    }
    
    public void removeGroupPurchase(Purchase p){
        this.groupPurchases.remove(p);
    }
}
