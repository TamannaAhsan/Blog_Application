package com.example.blog_application.payloads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;
    @NotEmpty
    @Size(min=5)
    private String postTitle;
    @NotEmpty
    @Size(min=5)
    private String content;
    private String imageName;
    @NotEmpty
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comment = new HashSet<>();

}
