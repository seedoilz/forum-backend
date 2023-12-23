package com.wanted.project.dao;

import com.wanted.project.core.MongoDao;
import com.wanted.project.model.UserCommentLike;
import org.springframework.stereotype.Repository;

@Repository
public class UserCommentLikeDao extends MongoDao<UserCommentLike> {

    @Override
    protected Class<UserCommentLike> getEntityClass() {
        return UserCommentLike.class;
    }
}
