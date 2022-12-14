package com.novin.accountmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(name = "userName")
    private String userName;
    @NotNull
    private Date deadLine;
    @NotEmpty
    private String itemName;
    @NotEmpty
    private String comment;
    @NotNull
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private User user;

    public Account(Account account, User owner) {
        this.setComment(account.getComment());
        this.setDeadLine(account.getDeadLine());
        this.setPrice(account.getPrice());
        this.setUserName(account.getUserName());
        this.setItemName(account.getItemName());
        this.setUser(owner);
    }

    public Account() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String customerName) {
        this.userName = customerName;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
