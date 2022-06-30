package com.bookstore.backen.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cartorder")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class CartOrder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cart_order")
    private int idCartOrder;
    @Basic
    @Column(name = "book_name")
    private String bookName;
    @Basic
    @Column(name = "bookid")
    private Integer bookid;
    @Basic
    @Column(name = "buy_num")
    private Integer buyNum;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "belongto_orderid")
    private Integer belongtoOrderid;
    @Basic
    @Column(name = "submit_status")
    private int submitStatus;
    @Basic
    @Column(name = "belongto_User")
    private String belongtoUser;
    @Basic
    @Column(name = "createtime")
    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getIdCartOrder() {
        return idCartOrder;
    }

    public void setIdCartOrder(int idCartOrder) {
        this.idCartOrder = idCartOrder;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBelongtoOrderid() {
        return belongtoOrderid;
    }

    public void setBelongtoOrderid(Integer belongtoOrderid) {
        this.belongtoOrderid = belongtoOrderid;
    }

    public Integer getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getBelongtoUser() {
        return belongtoUser;
    }

    public void setBelongtoUser(String belongtoUser) {
        this.belongtoUser = belongtoUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartOrder cartorder = (CartOrder) o;
        return idCartOrder == cartorder.idCartOrder && Objects.equals(bookName, cartorder.bookName) && Objects.equals(bookid, cartorder.bookid) && Objects.equals(buyNum, cartorder.buyNum) && Objects.equals(price, cartorder.price) && Objects.equals(belongtoOrderid, cartorder.belongtoOrderid) && Objects.equals(submitStatus, cartorder.submitStatus) && Objects.equals(belongtoUser, cartorder.belongtoUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCartOrder, bookName, bookid, buyNum, price, belongtoOrderid, submitStatus, belongtoUser);
    }
}
