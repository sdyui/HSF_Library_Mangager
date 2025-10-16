public class Main {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();
            System.out.println(" Kết nối thành công!");
            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println(" Kết nối thất bại!");
            e.printStackTrace();
        }
    }