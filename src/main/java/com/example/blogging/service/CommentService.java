package com.example.blogging.service;

import com.example.blogging.dto.CommentResponse;
import com.example.blogging.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    public CommentResponse createComment(Comment comment, Long id, String username);
    public Optional<CommentResponse> getCommentById(Long id);

    public void deleteCommentById(Long id, String username);
    public CommentResponse updateComment(Long id, Comment comment, String username);

    Page<Comment> getAllComments(PageRequest pageable);

    List<CommentResponse> getComments();
}
