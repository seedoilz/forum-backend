package com.wanted.project.dao;

import com.wanted.project.core.Mapper;
import com.wanted.project.model.Like;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper extends Mapper<Like> {

    Like findLike(@Param("userId") Long userId, @Param("postId") String postId);
}