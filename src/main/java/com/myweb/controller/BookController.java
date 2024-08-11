package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.BookDTO;
import com.myweb.entity.Book;
import com.myweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @GetMapping("/{bookId}")
    public ApiResponse<Book> getBookById(@PathVariable String bookId){
        return ApiResponse.<Book>builder()
                .success(true)
                .result(bookService.getBookById(bookId))
                .build();
    }
    @GetMapping("")
    public ApiResponse<List<Book>> getBooks(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {

        if (minPrice != null && maxPrice != null) {
            // Return books within price range
            return ApiResponse.<List<Book>>builder()
                    .success(true)
                    .result(bookService.getBooksByPriceRange(minPrice, maxPrice))
                    .build();

        } else if (sortBy != null && order != null) {

            return ApiResponse.<List<Book>>builder()
                    .success(true)
                    .result(bookService.getBooksSortedBy(sortBy, order))
                    .build();
        } else {
            // Default: Return all books
            return ApiResponse.<List<Book>>builder()
                    .success(true)
                    .result(bookService.getAllBooks())
                    .build();
        }
    }

    @PutMapping("/update/{bookId}")
    public ApiResponse<Book> updateBook(@PathVariable String bookId, @RequestBody BookDTO bookDTO){
        return ApiResponse.<Book>builder()
                .success(true)
                .result(bookService.updateBook(bookId, bookDTO))
                .build();
    }

    @PostMapping(value = "/new-book", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Book> createBook(
            @RequestPart("book") BookDTO bookDTO,
            @RequestPart("images") List<MultipartFile> images) throws IOException {

        return ApiResponse.<Book>builder()
                .success(true)
                .result(bookService.createBook(bookDTO, images))
                .build();
    }
    @DeleteMapping("/{bookId}")
    ApiResponse<Book> deleteBook(@PathVariable String bookId){
        bookService.deleteBook(bookId);
        return ApiResponse.<Book>builder()
                .success(true)
                .message("Delete book successfully")
                .build();
    }

}
