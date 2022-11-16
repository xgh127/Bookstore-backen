package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.EsBookDao;
import com.bookstore.backen.entity.esBook;
import com.bookstore.backen.repository.EsBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

@Repository
public class EsBookDaoImpl implements EsBookDao {
    @Autowired
    EsBookRepository esBookRepository;
    @Override
    public SearchHits<esBook> findEsBooksByDescription(String keyword) {
            return esBookRepository.findByDescription(keyword);
    }
}
