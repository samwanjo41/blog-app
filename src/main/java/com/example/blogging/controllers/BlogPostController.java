package com.example.blogging.controllers;


import com.example.blogging.dto.BlogPostResponse;
import com.example.blogging.entity.BlogPost;
import com.example.blogging.service.BlogPostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class BlogPostController {
    @Autowired
    BlogPostService blogPostService;

    @PostMapping("/blog")
    public BlogPostResponse saveBlog(@RequestBody BlogPost blogPost, HttpServletRequest request) {
        String username = getUsernameFromHeader(request);
        return blogPostService.createBlogPost(blogPost, username);
    }

    public String getUsernameFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = blogPostService.extractUsernameFromToken(token);
        return username;
    }

    @GetMapping("/search")
    public ResponseEntity<List<BlogPostResponse>> searchBlogPosts(@RequestParam("keyword") String keyword) {
        List<BlogPostResponse> searchResults = blogPostService.searchBlogPosts(keyword);
        return ResponseEntity.ok(searchResults);
    }
    @GetMapping("/blog")
    public ResponseEntity<List<BlogPost>> getAllBlogPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<BlogPost> blogPostPage = blogPostService.getAllBlogPosts(pageable);
        return ResponseEntity.ok(blogPostPage.getContent());
    }

    @GetMapping("/posts")
    public ResponseEntity<List<BlogPostResponse>>findallBlogs() {
        return ResponseEntity.ok(blogPostService.getAllBlogs());
    }

    @GetMapping("/blog/{id}")
    public Optional<BlogPostResponse> getBlogPost(@PathVariable Long id) {
      return blogPostService.getBlogPostById(id);
    }

    @PutMapping("/blog/{id}")
    public BlogPostResponse updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPost, HttpServletRequest request) {
        String username = getUsernameFromHeader(request);
      return  blogPostService.updateBlogPost(updatedBlogPost, username);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id, HttpServletRequest request) {
        String username = getUsernameFromHeader(request);
        blogPostService.deleteBlogPostById(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping("/blog")
//    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
//        return blogPostService.findAllB();
//    }
}
