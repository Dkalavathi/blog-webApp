package stu.mypro.blogpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import stu.mypro.blogpro.dto.CommentDto;
import stu.mypro.blogpro.dto.PostDto;
import stu.mypro.blogpro.service.PostService;

import java.util.List;

@Controller
public class BlogController {

    public PostService postService;

    public BlogController(PostService postService) {

        this.postService = postService;
    }


//handler method to handle http://localhost:8080/

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        List<PostDto> postsResponse = postService.findAllPosts();
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_post";
    }

//handler method to handle view post request

    @GetMapping("/post/{postUrl}")
    public String showPost(@PathVariable("postUrl") String postUrl,
                       Model model) {
    PostDto post = postService.findPostByUrl(postUrl);
    CommentDto commentDto = new CommentDto();
    model.addAttribute("post", post);
    model.addAttribute("comment", commentDto);
    return "blog/blog_post";
}

    //handler method to handle post search request
    // http://localhost:8080/blog/posts/search?query=java

    @GetMapping("blog/post/search")
    public String searchPosts(@RequestParam(value="query") String query,
                           Model model) {
        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_post";
    }

}

