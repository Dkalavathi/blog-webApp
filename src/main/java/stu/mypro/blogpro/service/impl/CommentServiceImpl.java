package stu.mypro.blogpro.service.impl;

import org.springframework.stereotype.Service;
import stu.mypro.blogpro.dto.CommentDto;
import stu.mypro.blogpro.entity.Comment;
import stu.mypro.blogpro.entity.Post;
import stu.mypro.blogpro.entity.User;
import stu.mypro.blogpro.mapper.CommentMapper;
import stu.mypro.blogpro.repository.CommentRepository;
import stu.mypro.blogpro.repository.PostRepository;
import stu.mypro.blogpro.repository.UserRepository;
import stu.mypro.blogpro.service.CommentService;
import stu.mypro.blogpro.util.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
  public void createComment(String postUrl, CommentDto commentDto){

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
  }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map((comment)->CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }

}
