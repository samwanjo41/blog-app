package com.example.blogging.controllers;

import com.example.blogging.dto.CommentResponse;
import com.example.blogging.entity.Comment;
import com.example.blogging.service.BlogPostService;
import com.example.blogging.service.CommentService;
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
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    BlogPostService blogPostService;

    @Autowired
    BlogPostController blogPostController;

    @PostMapping("/comment/{id}")
    public CommentResponse saveComment(@RequestBody Comment comment, @PathVariable("id") Long id, HttpServletRequest request) {
        String username = blogPostController.getUsernameFromHeader(request);
        return commentService.createComment(comment, id, username);
    }

    @GetMapping("/comment")
    public ResponseEntity<List<Comment>> getAllComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<Comment> commentPage = commentService.getAllComments(pageable);
        return ResponseEntity.ok(commentPage.getContent());
    }

    @GetMapping("/comment/{id}")
    public Optional<CommentResponse> getComment(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("/comment/{id}")
    public CommentResponse updateComment(@PathVariable Long id, @RequestBody Comment updatedComment, HttpServletRequest request) {
        String username = blogPostController.getUsernameFromHeader(request);
        return  commentService.updateComment(id, updatedComment, username);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, HttpServletRequest request) {
        String username = blogPostController.getUsernameFromHeader(request);
        commentService.deleteCommentById(id, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> getAllComments(){
        return ResponseEntity.ok(commentService.getComments());
    }
}
