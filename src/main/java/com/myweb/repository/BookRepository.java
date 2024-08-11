package com.myweb.repository;

import com.myweb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query(value = "SELECT * FROM books WHERE book_price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<Book> getBooksByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);


}
