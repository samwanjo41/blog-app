package com.example.blogging.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;
    @Column(name = "content")
    private String content;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    @Column(name = "username")
    private String user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    BlogPost blogPost;
}
