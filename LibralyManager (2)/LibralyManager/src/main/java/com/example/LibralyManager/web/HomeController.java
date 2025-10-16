package com.example.LibralyManager.web;

import com.example.LibralyManager.repository.BookRepository;
import com.example.LibralyManager.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public HomeController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }
    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("pageTitle", "Books List");
        model.addAttribute("books", List.of(
                Map.of("id", 1, "title", "Clean Code", "isbn", "978-0132350884"),
                Map.of("id", 2, "title", "Effective Java", "isbn", "978-0134685991")
        ));
        return "books";
    }

}


