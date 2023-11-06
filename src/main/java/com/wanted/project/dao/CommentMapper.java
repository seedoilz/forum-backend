package com.wanted.project.dao;

import com.wanted.project.core.Mapper;
import com.wanted.project.model.Comment;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    Integer createComment(Comment commentP0);
}