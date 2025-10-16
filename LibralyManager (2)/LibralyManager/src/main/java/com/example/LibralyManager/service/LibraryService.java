package com.example.LibralyManager.service;

import com.example.LibralyManager.domain.*;
import com.example.LibralyManager.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class LibraryService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public LibraryService(UserRepository userRepository,
                          BookRepository bookRepository,
                          LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public Loan borrowBook(Long userId, Long bookId, LocalDate dueDate) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.isBanned() || user.isDeleted()) throw new IllegalStateException("User is banned or deleted");
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        long currentMonthLoans = loanRepository.countByUserIdAndLoanDateBetween(
                userId,
                LocalDate.now().withDayOfMonth(1),
                LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())
        );

        int limit = switch (user.getMembershipTier()) {
            case ORDINARY -> 5;
            case ADVANCE -> 10;
            case PREMIUM -> Integer.MAX_VALUE;
        };
        if (currentMonthLoans >= limit) throw new IllegalStateException("Monthly loan limit reached");

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setDueDate(dueDate);
        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        if (loan.getReturnDate() != null) return loan; // already returned
        loan.setReturnDate(returnDate);

        if (returnDate.isAfter(loan.getDueDate())) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);
            long fine = daysLate * 30000L; // 30k/ngày/quyển
            User user = loan.getUser();
            if (user.getBalanceVnd() < fine) {
                user.setBanned(true);
                user.setDeleted(true); // soft delete/ban
            } else {
                user.setBalanceVnd(user.getBalanceVnd() - fine);
            }
            userRepository.save(user);
        }
        return loanRepository.save(loan);
    }
}


