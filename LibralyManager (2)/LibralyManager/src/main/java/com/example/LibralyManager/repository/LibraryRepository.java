package com.example.LibralyManager.repository;

import com.example.LibralyManager.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByNameIgnoreCase(String name);
}


