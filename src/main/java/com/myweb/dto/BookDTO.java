package com.myweb.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {
    String publisher;
    Integer publisherYear;
    String title;
    String bookDescription;
    BigDecimal bookPrice;
    BigDecimal rating;
    String authorId;
    List<ImageDTO> images;
    List<CategoryDTO> categories;

}
