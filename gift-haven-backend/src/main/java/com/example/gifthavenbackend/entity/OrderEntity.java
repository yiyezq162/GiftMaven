package com.example.gifthavenbackend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */
@Entity
@Table(name = "order", schema = "gift_shop", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "customer_id")
    private Integer customerId;
    @Basic
    @Column(name = "create_at")
    private Timestamp createAt;
    @Basic
    @Column(name = "update_t")
    private Timestamp updateT;
    @Basic
    @Column(name = "order_status")
    private String orderStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateT() {
        return updateT;
    }

    public void setUpdateT(Timestamp updateT) {
        this.updateT = updateT;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return orderId == that.orderId && Objects.equals(customerId, that.customerId) && Objects.equals(createAt, that.createAt) && Objects.equals(updateT, that.updateT) && Objects.equals(orderStatus, that.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, createAt, updateT, orderStatus);
    }
}
