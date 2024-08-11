package com.myweb.repository;

import com.myweb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByBook_BookId(String bookId);  // Cập nhật theo thuộc tính đúng của Book
    void deleteByBook_BookId(String bookId);  // Cập nhật theo thuộc tính đúng của Book
}

