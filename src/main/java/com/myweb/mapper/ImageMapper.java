package com.myweb.mapper;

import com.myweb.dto.ImageDTO;
import com.myweb.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toImage(ImageDTO imageDTO);

    ImageDTO toImageDTO(Image image);
}
