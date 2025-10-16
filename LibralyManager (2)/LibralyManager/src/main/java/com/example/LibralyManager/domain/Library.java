package com.example.LibralyManager.domain;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libraries")
public class Library extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Manager> managers = new HashSet<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Manager> getManagers() { return managers; }
}


