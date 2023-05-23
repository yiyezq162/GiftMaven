package com.example.gifthavenbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
@Entity
@Table(name = "order_product", schema = "gift_shop", catalog = "")
@Where(clause = "deleted = '0'")
public class OrderProductEntity {

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "gift_id")
    @JsonIgnoreProperties("order_product")
    private GiftsEntity giftsEntity;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "order_id")
    private Integer orderId;
    @Basic
    @Column(name = "gift_id",insertable = false, updatable = false)
    private Integer giftId;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "deleted")
    private String deleted;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public GiftsEntity getGiftsEntity() {
        return giftsEntity;
    }

    public void setGiftsEntity(GiftsEntity giftsEntity) {
        this.giftsEntity = giftsEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProductEntity that = (OrderProductEntity) o;

        if (productId != that.productId) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (giftId != null ? !giftId.equals(that.giftId) : that.giftId != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (giftId != null ? giftId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
