package com.example.blogging.dto;

import com.example.blogging.entity.BlogPost;
import com.example.blogging.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long id;

    private String content;

    private String user;


}
