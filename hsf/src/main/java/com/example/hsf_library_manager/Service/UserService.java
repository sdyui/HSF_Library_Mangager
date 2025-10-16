

import com.library.entity.*;
import jakarta.persistence.*;
import java.util.List;

public class UserService {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("libraryPU");

    public String deposit(String username, double amount) {
        EntityManager em = emf.createEntityManager();
        User user = findByUsername(em, username);
        if (user == null) return "Không tìm thấy người dùng";

        em.getTransaction().begin();
        user.deposit(amount);
        em.merge(user);
        em.getTransaction().commit();
        return "Nạp " + amount + "đ thành công!";
    }

    public String upgradeMembership(String username, MembershipType type) {
        EntityManager em = emf.createEntityManager();
        User user = findByUsername(em, username);
        if (user == null) return "Không tìm thấy người dùng";

        double fee = type.getFee();
        if (user.getBalance() < fee) return "Số dư không đủ!";
        em.getTransaction().begin();
        user.deduct(fee);
        user.setMembership(type);
        em.merge(user);
        em.getTransaction().commit();
        return "Đã nâng cấp lên gói " + type.name();
    }

    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    private User findByUsername(EntityManager em, String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :n", User.class)
                    .setParameter("n", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
