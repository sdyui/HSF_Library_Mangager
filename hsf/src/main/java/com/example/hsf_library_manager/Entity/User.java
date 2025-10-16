package com.example.hsf_library_manager.Entity;
import package.
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_account")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private double balance;
    private boolean banned = false;

    @Enumerated(EnumType.STRING)
    private MembershipType membership = MembershipType.ORDINARY;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowedBooks = new ArrayList<>();

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void deduct(double amount) {
        if (balance >= amount) balance -= amount;
        else banned = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public MembershipType getMembership() {
        return membership;
    }

    public void setMembership(MembershipType membership) {
        this.membership = membership;
    }

    public List<BorrowRecord> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowRecord> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    // getters/setters
}
