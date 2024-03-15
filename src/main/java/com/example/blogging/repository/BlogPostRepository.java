package com.example.blogging.repository;

import com.example.blogging.dto.BlogPostResponse;
import com.example.blogging.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    @Query("SELECT bp FROM BlogPost bp WHERE bp.title LIKE %:keyword% OR bp.content LIKE %:keyword%")
    List<BlogPost> searchByTitleOrContent(@Param("keyword") String keyword);

}