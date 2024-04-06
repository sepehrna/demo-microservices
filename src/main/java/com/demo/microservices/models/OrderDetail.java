package com.demo.microservices.models;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

public class OrderDetail {

    @Field("product_id")
    private Integer productId;

    @Field("quantity")
    private Float quantity;

    @Field("unit_price")
    private BigDecimal unitPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
