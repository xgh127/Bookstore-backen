package com.bookstore.backen.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "belong_user")
    private String belongUser;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "destination")
    private String destination;
    @Basic
    @Column(name = "postcode")
    private String postcode;
    @Basic
    @Column(name = "receiver_name")
    private String receiverName;
    @Basic
    @Column(name = "total_price")
    private Integer totalPrice;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "belongto_orderid")
    List<CartOrder> cartOrderList;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "belong_order_id")
    List<Orderitem> orderitemList;

    public List<Orderitem> getOrderitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }

    public List<CartOrder> getCartOrderList() {
        return cartOrderList;
    }

    public void setCartOrderList(List<CartOrder> cartOrderList) {
        this.cartOrderList = cartOrderList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(belongUser, order.belongUser) && Objects.equals(phoneNumber, order.phoneNumber) && Objects.equals(destination, order.destination) && Objects.equals(postcode, order.postcode) && Objects.equals(receiverName, order.receiverName) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(createTime, order.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, belongUser, phoneNumber, destination, postcode, receiverName, totalPrice, createTime);
    }
}
