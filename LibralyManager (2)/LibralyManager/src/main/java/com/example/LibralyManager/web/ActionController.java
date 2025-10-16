package com.example.LibralyManager.web;

import com.example.LibralyManager.domain.Book;
import com.example.LibralyManager.domain.MembershipTier;
import com.example.LibralyManager.repository.BookRepository;
import com.example.LibralyManager.service.AccountService;
import com.example.LibralyManager.service.LibraryService;
import com.example.LibralyManager.service.ManagerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/action")
public class ActionController {
    private final LibraryService libraryService;
    private final AccountService accountService;
    private final ManagerService managerService;

    public ActionController(LibraryService libraryService, AccountService accountService, ManagerService managerService) {
        this.libraryService = libraryService;
        this.accountService = accountService;
        this.managerService = managerService;
    }

    @PostMapping("/borrow")
    public String borrow(@RequestParam Long userId,
                         @RequestParam Long bookId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        libraryService.borrowBook(userId, bookId, dueDate);
        return "redirect:/";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long loanId,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        libraryService.returnBook(loanId, returnDate);
        return "redirect:/";
    }

    @PostMapping("/topup")
    public String topUp(@RequestParam Long userId, @RequestParam long amount) {
        accountService.topUp(userId, amount);
        return "redirect:/";
    }

    @PostMapping("/upgrade")
    public String upgrade(@RequestParam Long userId, @RequestParam MembershipTier tier) {
        accountService.upgradeMembership(userId, tier);
        return "redirect:/";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String title, @RequestParam String isbn) {
        managerService.addBook(title, isbn);
        return "redirect:/manager";
    }
    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId) {
        managerService.deleteBook(bookId);
        return "redirect:/manager";
    }
}


