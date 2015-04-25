package server.entity;

/**
 *
 * @author magni
 */
public class Item {
    protected int itemId;
    protected String itemName;
    protected String itemAnnotation;
    protected int itemQuantity;
    protected float itemPrice;

    public Item(String itemName, int itemQuantity) {
        this.itemName = itemName;
        this.itemId = 0;
        this.itemQuantity = itemQuantity;
        this.itemAnnotation = "";
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemAnnotation() {
        return itemAnnotation;
    }

    public void setItemAnnotation(String itemAnnotation) {
        this.itemAnnotation = itemAnnotation;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
