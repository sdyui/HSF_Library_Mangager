

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow_record")
public class BorrowRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private User user;
    @ManyToOne private Book book;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned = false;

    public double getFine() {
        if (!returned && LocalDate.now().isAfter(dueDate)) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            return daysLate * 30000;
        }
        return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters/setters
}
