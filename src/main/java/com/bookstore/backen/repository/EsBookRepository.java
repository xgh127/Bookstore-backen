package com.bookstore.backen.repository;

import com.bookstore.backen.entity.esBook;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsBookRepository extends ElasticsearchRepository<esBook,Integer> {
    List<esBook> findAll();
    @Highlight(fields = {
            @HighlightField(name = "description")
    })
    @Query("{\"multi_match\":{\"fields\": [\"description\"],\"query\":\"?0\"}}")
    SearchHits<esBook> findByDescription(String keyword);
}
