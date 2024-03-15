package com.example.blogging.service;

import com.example.blogging.dto.CommentResponse;
import com.example.blogging.entity.BlogPost;
import com.example.blogging.entity.Comment;
import com.example.blogging.entity.Role;
import com.example.blogging.entity.User;
import com.example.blogging.repository.BlogPostRepository;
import com.example.blogging.repository.CommentRepository;
import com.example.blogging.repository.UserRepository;
import com.example.blogging.util.BlogPostMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Override
    public CommentResponse createComment(Comment comment, Long id, String username) {

        BlogPost post = blogPostRepository.getById(id);
        comment.setUser(username);
        comment.setBlogPost(post);
        commentRepository.save(comment);

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .user(username)
                .build();

    }

    @Override
    public Optional<CommentResponse> getCommentById(Long id) {
        return Optional.ofNullable(BlogPostMapper.maptoCommentDto(commentRepository.findById(id).get()));
    }

    @Override
    public void deleteCommentById(Long id, String username) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        }

        Comment existingComment = commentRepository.findById(id).get();

        User user = userRepository.findByUsername(username).get();

        if(user.getRole().equals(Role.ADMIN)) {
            commentRepository.deleteById(id);
        } else if (user.getRole().equals(Role.USER)) {
            //check if user is the author of the comment
            if (!existingComment.getUser().equals(username)) {
                throw new IllegalArgumentException("You are not authorized to update this comment");
            }
            commentRepository.deleteById(id);
        }


    }

    @Override
    public CommentResponse updateComment(Long id, Comment updatedComment, String username) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));

        //check if user is the author of the comment
        if (!existingComment.getUser().equals(username)) {
            throw new IllegalArgumentException("You are not authorized to update this comment");
        }

        existingComment.setContent(updatedComment.getContent());
        return BlogPostMapper.maptoCommentDto(commentRepository.save(existingComment));
    }
    @Override
    public Page<Comment> getAllComments(PageRequest pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public List<CommentResponse> getComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for(Comment comment: comments){
            commentResponses.add(BlogPostMapper.maptoCommentDto(comment));
        }
       return commentResponses;
    }
}