

import com.library.entity.*;
import jakarta.persistence.*;
import java.time.LocalDate;

public class BorrowService {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("libraryPU");

    public String borrowBook(User user, Book book, LocalDate dueDate) {
        if (user.isBanned()) return "Tài khoản bị khóa!";
        if (!book.isAvailable()) return "Sách hiện không khả dụng!";

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(dueDate);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        book.setAvailable(false);
        em.persist(record);
        em.merge(book);
        em.getTransaction().commit();
        return "Mượn sách thành công!";
    }

    public String returnBook(BorrowRecord record) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        double fine = record.getFine();
        if (fine > 0) record.getUser().deduct(fine);
        record.setReturned(true);
        record.getBook().setAvailable(true);
        em.merge(record);
        em.getTransaction().commit();
        return fine > 0 ? "Trả sách trễ, phạt: " + fine + "đ" : "Trả sách thành công!";
    }
}
