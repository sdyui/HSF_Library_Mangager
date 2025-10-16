package com.example.LibralyManager.repository;

import com.example.LibralyManager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCase(String title);
    @Query("SELECT b FROM Book b WHERE b.deleted = false")
    List<Book> findAllActiveBooks();
}


