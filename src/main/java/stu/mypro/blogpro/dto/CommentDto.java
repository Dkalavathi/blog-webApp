package stu.mypro.blogpro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    @NotEmpty(message="Name should not be empty")
    private String name;

    @NotEmpty(message="email shoud not be empty")
    @Email
    private String email;

    @NotEmpty(message="content shoud not be empty")
    private String content;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
