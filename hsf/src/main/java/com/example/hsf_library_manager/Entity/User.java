

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

    // getters/setters
}
