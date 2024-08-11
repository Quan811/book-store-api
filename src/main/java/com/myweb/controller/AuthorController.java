package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.AuthorDTO;
import com.myweb.entity.Author;
import com.myweb.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @GetMapping("")
    ApiResponse<List<Author>> getAllAuthor(){
        return ApiResponse.<List<Author>>builder()
                .success(true)
                .result(authorService.getAllAuthors())
                .build();
    }

    @GetMapping("/{authorId}")
    ApiResponse<Author> getAuthorById(@PathVariable Long authorId){
        return ApiResponse.<Author>builder()
                .success(true)
                .result(authorService.getAuthorById(authorId))
                .build();
    }

    @PostMapping("/create-author")
    ApiResponse<Author> createAuthor(@RequestBody AuthorDTO authorDTO){
        return ApiResponse.<Author>builder()
                .success(true)
                .result(authorService.createAuthor(authorDTO))
                .build();
    }

    @PutMapping("/update/{authorId}")
    ApiResponse<Author> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorDTO authorDTO){
        return ApiResponse.<Author>builder()
                .success(true)
                .result(authorService.updateAuthor(authorId, authorDTO))
                .build();
    }

    @DeleteMapping("/{authorId}")
    ApiResponse<List<Author>> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return ApiResponse.<List<Author>>builder()
                .success(true)
                .message("Delete author successful!")
                .build();
    }
}
