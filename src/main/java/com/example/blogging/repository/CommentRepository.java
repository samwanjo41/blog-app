package com.example.blogging.repository;

import com.example.blogging.dto.CommentResponse;
import com.example.blogging.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
