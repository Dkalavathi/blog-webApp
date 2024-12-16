package stu.mypro.blogpro.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stu.mypro.blogpro.dto.CommentDto;
import stu.mypro.blogpro.dto.PostDto;
import stu.mypro.blogpro.service.CommentService;
import stu.mypro.blogpro.service.PostService;
import stu.mypro.blogpro.util.SecurityUtils;

import java.util.List;

@Controller
public class PostController {
 private enum ROLE { ROLE_GUEST, ROLE_ADMIN }
 private PostService postService;
 private CommentService commentService;


 public PostController(PostService postService,CommentService commentService) {
  this.postService = postService;
  this.commentService = commentService;
 }

 //create handler method,GET request and return model and view
 @GetMapping("/admin/posts")
 public String posts(Model model){
  String role = SecurityUtils.getRole();
  List<PostDto> posts = null;
  if(ROLE.ROLE_ADMIN.name().equals(role)) {
   posts = postService.findAllPosts();
  }else {
   posts = postService.findPostsByUser();
  }
  model.addAttribute("posts",posts);
  return "/admin/posts";
 }

 //handler method to handle list of comments request
 //localhost:8080/admin/posts/comments
 @GetMapping("/admin/posts/comments")
 public String postComments(Model model) {
  String role = SecurityUtils.getRole();
  List<CommentDto> comments = null;
  if (ROLE.ROLE_ADMIN.name().equals(role)){
   comments = commentService.findAllComments();
 }
 else
 {
  comments = commentService.findCommentsByPost();
 }
  model.addAttribute("comments",comments);
  return "admin/comments";
 }
//handler method to handle delete comment request
@GetMapping("/admin/posts/comments/{commentId}")
 public String deleteComment(@PathVariable("commentId") Long commentId){
  commentService.deleteComment(commentId);
  return "redirect:/admin/posts/comments";
 }

 //create handler method,to handle new post request
 @GetMapping("admin/posts/newpost")
 public String newpostForm(Model model){
  PostDto postDto = new PostDto();
  model.addAttribute("post",postDto);
  return "admin/create_post";
 }

//handler method to handle form submit request
 @PostMapping("/admin/posts")
 public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result,
                          Model model){
  if(result.hasErrors()){
   model.addAttribute("post",postDto);
   return "admin/create_post";
  }
  postDto.setUrl(getUrl(postDto.getTitle()));
  postService.createPost(postDto);
  return "redirect:/admin/posts";
 }

 //handler method to handle edit post request
@GetMapping("admin/posts/{postId}/edit")
public String editPostForm(@PathVariable("postId") Long postId,
                           Model model){
  PostDto postDto =postService.findPostById(postId);
  model.addAttribute("post",postDto);
  return "admin/edit_post";
}

 //handler method to handle update post request
 @PostMapping("admin/posts/{postId}")
 public String updatePost(@PathVariable("postId") Long postId,
                            @ModelAttribute("post") PostDto post,
                          BindingResult result,
                          Model model) {
  if (result.hasErrors()) {
   model.addAttribute("post", post);
   return "admin/edit_post";
  }
  post.setId(postId);
  postService.updatePost(post);
  return "redirect:/admin/posts";
 }
//handler method to handle delete post by request
 @GetMapping("/admin/posts/{postId}/delete")
 public String deletePost(@PathVariable("postId") Long postId){
   postService.deletePost(postId);
   return "redirect:/admin/posts";
 }

//handler method to handle view post
@GetMapping("/admin/posts/{postUrl}/view")
 public String viewPost(@PathVariable("postUrl") String postUrl,
                        Model model){
  PostDto postDto = postService.findPostByUrl(postUrl);
  model.addAttribute("post",postDto);
  return "admin/view_post";
 }

 //handler method to handle search blog request
 //localhost:8080/admin/posts/search/?query=java
 @GetMapping("/admin/posts/search")
 public String searchPosts(@RequestParam(value="query") String query,Model model){
  List<PostDto> posts = postService.searchPosts(query);
  model.addAttribute("posts",posts);
  return "admin/posts";
 }

 private static String getUrl(String postTitle){
  //OOPS Concepts Explained in Java---Reg expressions
  //oops-concepts-explained-in-java--Reg expressions
  String title = postTitle.trim().toLowerCase();
  String url = title.replaceAll("\\s+","-");
  url = url.replaceAll("[^A-Za-z0-9]","-");
  return url;
 }

}
