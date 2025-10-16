package com.example.LibralyManager.web;

import com.example.LibralyManager.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LookupController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public LookupController(AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String q, Model model) {
        String query = q == null ? "" : q;
        model.addAttribute("authors", authorRepository.findByNameContainingIgnoreCase(query));
        model.addAttribute("books", bookRepository.findByTitleContainingIgnoreCase(query));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("openLoans", loanRepository.findByReturnDateIsNull());
        model.addAttribute("q", query);
        return "search";
    }
}


