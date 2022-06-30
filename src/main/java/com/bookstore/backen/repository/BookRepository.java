package com.bookstore.backen.repository;

import com.bookstore.backen.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
                                        /*jpa后面的两个参数，一个*/
public interface BookRepository extends JpaRepository<Book,Integer> {

}
