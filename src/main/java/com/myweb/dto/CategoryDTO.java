package com.myweb.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CategoryDTO {
    Long categoryId;
    String categoryName;
}
