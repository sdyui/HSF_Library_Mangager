package com.example.LibralyManager.service;

import com.example.LibralyManager.domain.Book;
import com.example.LibralyManager.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {

    private final BookRepository bookRepository;

    public ManagerService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book addBook(String title, String isbn) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sách không được để trống");
        }
        if(isbn != null && bookRepository.findByIsbn(isbn).isPresent()){
            throw new IllegalArgumentException("isbn đã tồn tại");
        }

        Book book = new Book();
        book.setTitle(title.trim());
        book.setIsbn(isbn != null ? isbn.trim() : null);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
       Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy" + id));
    if(book.isDeleted()){
        throw new IllegalArgumentException("đã xoá sách này trước đó");
    }
    book.setDeleted(true);
    bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public long countBooks() {
        return bookRepository.count();
    }
}
