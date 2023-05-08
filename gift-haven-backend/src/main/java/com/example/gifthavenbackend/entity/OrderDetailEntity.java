package com.example.gifthavenbackend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */
@Entity
@Table(name = "order_detail", schema = "gift_shop", catalog = "")
public class OrderDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_id")
    private int orderDetailId;
    @Basic
    @Column(name = "order_id")
    private Integer orderId;
    @Basic
    @Column(name = "gift_id")
    private Integer giftId;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "amount")
    private String amount;
    @Basic
    @Column(name = "create_at")
    private Timestamp createAt;
    @Basic
    @Column(name = "update_at")
    private Timestamp updateAt;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailEntity that = (OrderDetailEntity) o;
        return orderDetailId == that.orderDetailId && Objects.equals(orderId, that.orderId) && Objects.equals(giftId, that.giftId) && Objects.equals(number, that.number) && Objects.equals(amount, that.amount) && Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailId, orderId, giftId, number, amount, createAt, updateAt);
    }
}
