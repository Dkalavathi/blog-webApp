package stu.mypro.blogpro.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message="title should not be empty")
    private String title;

    private String url;
    @NotEmpty(message="content should not be empty")
    private String content;
    @NotEmpty(message="shortDescription should not be empty")
    private String shortDescription;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;

}
