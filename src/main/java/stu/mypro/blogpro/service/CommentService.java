package stu.mypro.blogpro.service;

import org.springframework.stereotype.Service;
import stu.mypro.blogpro.dto.CommentDto;

import java.util.List;

@Service
public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();
    void deleteComment(Long commentId);

    List<CommentDto> findCommentsByPost();
}
