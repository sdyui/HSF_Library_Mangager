package com.example.LibralyManager.repository;

import com.example.LibralyManager.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserIdAndReturnDateIsNull(Long userId);
    List<Loan> findByReturnDateIsNull();
    long countByUserIdAndLoanDateBetween(Long userId, LocalDate start, LocalDate end);
}


