package com.example.authorservice.Repository;

import com.example.authorservice.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

;

/*jpa后面的两个参数，一个*/
public interface BookRepository extends JpaRepository<Book,Integer> {

}