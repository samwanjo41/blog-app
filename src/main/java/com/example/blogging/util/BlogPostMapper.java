package com.example.blogging.util;

import com.example.blogging.dto.BlogPostResponse;
import com.example.blogging.dto.CommentResponse;
import com.example.blogging.entity.BlogPost;
import com.example.blogging.entity.Comment;
import org.modelmapper.ModelMapper;

public class BlogPostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static BlogPostResponse mapToResponseDto(BlogPost blogPost) {
        BlogPostResponse responseDto = modelMapper.map(blogPost, BlogPostResponse.class);
        responseDto.setAuthor(blogPost.getUser()); // Assuming User has a getUsername() method
        return responseDto;
    }


    public static CommentResponse maptoCommentDto(Comment comment) {
        CommentResponse responseDto = modelMapper.map(comment, CommentResponse.class);
//        responseDto.setUser(comment.getUser().getUsername()); // Assuming User has a getUsername() method
        return responseDto;
    }

}
