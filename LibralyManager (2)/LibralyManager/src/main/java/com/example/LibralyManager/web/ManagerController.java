package com.example.LibralyManager.web;

import com.example.LibralyManager.repository.BookRepository;
import com.example.LibralyManager.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ManagerController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/manager")
    public String managerDashboard(Model model) {
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("bookCount", bookRepository.count());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAllActiveBooks());
        return "manager"; // trỏ tới templates/manager.html
    }
}
