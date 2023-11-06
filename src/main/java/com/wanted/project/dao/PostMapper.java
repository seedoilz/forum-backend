package com.wanted.project.dao;

import com.wanted.project.core.Mapper;
import com.wanted.project.model.Post;

import java.util.List;

public interface PostMapper extends Mapper<Post> {

    int createPost(Post postPO);

    int updatePost(Post postPO);

    List<Post> findPostsByUserId(long userId);
}
