package com.bookStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStore.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}