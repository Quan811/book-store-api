package com.myweb.mapper;

import com.myweb.dto.AuthorDTO;
import com.myweb.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorDTO authorDTO);
    AuthorDTO toAuthorDTO(Author author);

    @Mapping(target = "authorId", ignore = true)
    void updateAuthor(AuthorDTO authorDTO, @MappingTarget Author author);
}
