package com.example.blog_application.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotEmpty
    @Size(min=5, message = "Minimum size of category title is 5")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10, message = "Minimum size of category description is 10")
    private String categoryDescription;
}
