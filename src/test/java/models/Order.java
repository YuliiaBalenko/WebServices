package models;


import com.google.common.collect.ImmutableList;

import java.util.List;

public class Order {

    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }


    public static Order createOrder() {

        Order order = new Order();
        order.setPetId(35);
        order.setQuantity(3);
        order.setShipDate("2020-10-15T22:32:22.873Z");
        order.setStatus("approved");
        order.setComplete(true);
        return order;
    }
}

