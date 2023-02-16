package com.example.psi.creators.repository;

import com.example.psi.creators.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("Select A from Author A where A.userName=:username")
    Optional<Author> findAuthorByUsername(String username);


}
