package com.bookstore.backen.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Orderitem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_item_id")
    private int orderItemId;
    @Basic
    @Column(name = "belong_user")
    private String belongUser;
    @Basic
    @Column(name = "buy_num")
    private Integer buyNum;
    @Basic
    @Column(name = "isbn")
    private String isbn;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "finsih_time")
    private Timestamp finsihTime;
    @Basic
    @Column(name = "belong_order_id")
    private Integer belongOrderId;
    @Basic
    @Column(name = "book_id")
    private Integer bookId;

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getFinsihTime() {
        return finsihTime;
    }

    public void setFinsihTime(Timestamp finsihTime) {
        this.finsihTime = finsihTime;
    }

    public Integer getBelongOrderId() {
        return belongOrderId;
    }

    public void setBelongOrderId(Integer belongOrderId) {
        this.belongOrderId = belongOrderId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderitem orderitem = (Orderitem) o;
        return orderItemId == orderitem.orderItemId && price == orderitem.price && Objects.equals(belongUser, orderitem.belongUser) && Objects.equals(buyNum, orderitem.buyNum) && Objects.equals(isbn, orderitem.isbn) && Objects.equals(name, orderitem.name) && Objects.equals(author, orderitem.author) && Objects.equals(description, orderitem.description) && Objects.equals(type, orderitem.type) && Objects.equals(image, orderitem.image) && Objects.equals(finsihTime, orderitem.finsihTime) && Objects.equals(belongOrderId, orderitem.belongOrderId) && Objects.equals(bookId, orderitem.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, belongUser, buyNum, isbn, price, name, author, description, type, image, finsihTime, belongOrderId, bookId);
    }
}
