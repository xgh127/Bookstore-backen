package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.esBook;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface EsBookDao {
    SearchHits<esBook> findEsBooksByDescription(String keyword);
}
