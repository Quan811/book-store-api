package com.myweb.mapper;

import com.myweb.dto.BookDTO;
import com.myweb.entity.Book;
import com.myweb.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "authorId", target = "author.authorId")
    @Mapping(source = "categories", target = "categories")
    @Mapping(source = "images", target = "images")
    Book toBook(BookDTO bookDTO);

    @Mapping(source = "author.authorId", target = "authorId")
    @Mapping(source = "categories", target = "categories")
    @Mapping(source = "images", target = "images")
    BookDTO toBookDTO(Book book);

    @Mapping(source = "authorId", target = "author.authorId")
    @Mapping(source = "categories", target = "categories")
    @Mapping(source = "images", target = "images")
    void updateBook(BookDTO bookDTO, @MappingTarget Book book);

}
