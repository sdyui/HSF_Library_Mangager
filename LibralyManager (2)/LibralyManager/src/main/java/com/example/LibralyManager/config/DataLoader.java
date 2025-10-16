package com.example.LibralyManager.config;

import com.example.LibralyManager.domain.*;
import com.example.LibralyManager.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(LibraryRepository libraryRepository,
                               ManagerRepository managerRepository,
                               AuthorRepository authorRepository,
                               BookRepository bookRepository,
                               UserRepository userRepository) {
        return args -> {
            if (libraryRepository.count() == 0) {
                Library lib = new Library();
                lib.setName("Main Library");
                libraryRepository.save(lib);

                Manager m = new Manager();
                m.setFullName("Admin Manager");
                m.setEmail("manager@example.com");
                m.setLibrary(lib);
                managerRepository.save(m);

                Author a1 = new Author(); a1.setName("Author One");
                Author a2 = new Author(); a2.setName("Author Two");
                authorRepository.save(a1); authorRepository.save(a2);

                Book b1 = new Book(); b1.setTitle("Spring in Action"); b1.getAuthors().add(a1);
                Book b2 = new Book(); b2.setTitle("JPA Mastery"); b2.getAuthors().add(a2);
                bookRepository.save(b1); bookRepository.save(b2);

                User u1 = new User();
                u1.setFullName("Nguyen Van A");
                u1.setEmail("a@example.com");
                u1.setBalanceVnd(150000);
                u1.setMembershipTier(MembershipTier.ORDINARY);
                userRepository.save(u1);
            }
        };
    }
}


