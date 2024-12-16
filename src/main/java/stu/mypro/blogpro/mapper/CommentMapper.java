package stu.mypro.blogpro.mapper;

import stu.mypro.blogpro.dto.CommentDto;
import stu.mypro.blogpro.entity.Comment;

public class CommentMapper {

    //convert comment entity to commentDto
    public static CommentDto mapToCommentDto(Comment comment){
       return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }

    //convert commentDto to comment entity
    public static Comment mapToComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }
}
