package server.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author magni
 */
public class Purchase {
    protected int purchaseId;
    protected Person purchaseOwner;
    protected String purchaseName;
    protected Date purchaseTime;
    protected List<Item> purchasedItems;

    public Purchase(Person purchaseOwner, String purchaseName, List<Item> purchasedItems) {
        this.purchaseId = 0;
        this.purchaseOwner = purchaseOwner;
        this.purchaseName = purchaseName;
        this.purchaseTime = new Date();
        this.purchasedItems = purchasedItems;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Person getPurchaseOwner() {
        return purchaseOwner;
    }

    public void setPurchaseOwner(Person purchaseOwner) {
        this.purchaseOwner = purchaseOwner;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<Item> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }
    
    public void addPurchasedItem(Item i){
        this.purchasedItems.add(i);
    }
    
    public void removePurchasedItem(Item i){
        this.purchasedItems.add(i);
    }
}
