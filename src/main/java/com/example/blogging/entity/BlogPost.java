package com.example.blogging.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;
    @Column(name = "post_title")
    private String title;
    @Column(name = "post_content")
    private String content;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "author_id")
    @Column(name = "author")
    private String user;
    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL)
    List<Comment> comments;


}
