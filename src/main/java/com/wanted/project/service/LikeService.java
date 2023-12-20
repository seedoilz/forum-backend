package com.wanted.project.service;
import com.wanted.project.model.Like;
import com.wanted.project.core.Service;


/**
 * Created by CodeGenerator on 2023/12/20.
 */
public interface LikeService extends Service<Like> {

    Long findLike(Long userId, String postId);
}
