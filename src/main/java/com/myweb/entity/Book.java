package com.myweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id")
    String bookId;
    String publisher;
    Integer publisherYear;
    String title;

    @Column(columnDefinition = "TEXT")
    String bookDescription;


    BigDecimal bookPrice;
    BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Review>  reviews;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    List<OrderItem> orderItems;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("imageId")
    List<Image> images;


    @ManyToMany
    @JoinTable(
            name = "book_category", // Tên bảng phụ
            joinColumns = @JoinColumn(name = "book_id"), // Khóa ngoại đến bảng Book
            inverseJoinColumns = @JoinColumn(name = "category_id") // Khóa ngoại đến bảng Category
    )
    @JsonIgnoreProperties("categoryId")
    Set<Category> categories = new HashSet<>();


}
