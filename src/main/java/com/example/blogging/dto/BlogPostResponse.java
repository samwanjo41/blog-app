package com.example.blogging.dto;

import com.example.blogging.entity.Comment;
import com.example.blogging.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostResponse {

    private Long id;

    private String title;

    private String content;

    String author;

    List<CommentResponse> comments;
}
