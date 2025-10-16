package com.example.LibralyManager.repository;

import com.example.LibralyManager.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByEmail(String email);
    List<Manager> findByLibraryId(Long libraryId);
}


