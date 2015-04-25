package server.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author magni
 */
public class Order {
    protected int orderId;
    protected String orderName;
    protected Date orderCreationTime;
    protected boolean isActual;
    protected List<Item> orderedItems;

    public Order(String orderName) {
        this.orderId = 0;
        this.orderName = orderName;
        this.orderCreationTime = new Date();
        this.isActual = true;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(Date orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    public boolean isIsActual() {
        return isActual;
    }

    public void setIsActual(boolean isActual) {
        this.isActual = isActual;
    }
    
    public void addOrderedItem(Item i){
        this.orderedItems.add(i);
    }
    
    public void removeOrderedItem(Item i){
        this.orderedItems.add(i);
    }
}
