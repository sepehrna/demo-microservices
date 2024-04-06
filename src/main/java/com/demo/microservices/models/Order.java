package com.demo.microservices.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;


/* In Spring-Boot, we don't need @Bson typically; the Spring-Boot handles it automatically with the following annotations:
@Document
@Id
@Field
*/
@Document(collection = "orders")
public class Order {

    @Id
    private String orderId;

    @Field("customer_id")
    private Integer customerId;

    @Field("ordered_on")
    @DateTimeFormat
    private Date orderedOn;

    private ArrayList<OrderDetail> orderDetails;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(Date orderedOn) {
        this.orderedOn = orderedOn;
    }

    public ArrayList<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
