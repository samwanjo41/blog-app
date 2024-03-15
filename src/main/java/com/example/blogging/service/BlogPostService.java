package com.example.blogging.service;


import com.example.blogging.dto.BlogPostResponse;
import com.example.blogging.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BlogPostService {
    public BlogPostResponse createBlogPost(BlogPost blogPost, String username);
    public Optional<BlogPostResponse> getBlogPostById(Long id);

    public void deleteBlogPostById(Long id, String username);
    public BlogPostResponse updateBlogPost(BlogPost blogPost, String username);


    Page<BlogPost> getAllBlogPosts(PageRequest pageable);

    List<BlogPostResponse> searchBlogPosts(String keyword);

    String extractUsernameFromToken(String token);

    List<BlogPostResponse> getAllBlogs();
}
