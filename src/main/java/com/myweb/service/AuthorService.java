package com.myweb.service;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.AuthorDTO;
import com.myweb.entity.Author;
import com.myweb.mapper.AuthorMapper;
import com.myweb.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorMapper authorMapper;
    public List<Author> getAllAuthors()
    {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId){
        return authorRepository.findById(authorId)
                .orElseThrow(()-> new RuntimeException("Author not found"));
    }

    public Author createAuthor(AuthorDTO authorDTO){
        Author author = authorMapper.toAuthor(authorDTO);
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long authorId, AuthorDTO authorDTO){
        Author author = getAuthorById(authorId);
        authorMapper.updateAuthor(authorDTO, author);
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId){
        if(getAuthorById(authorId)!=null){
            authorRepository.deleteById(authorId);
        }
        else{
            throw new RuntimeException("Author not found");
        }
    }


}
