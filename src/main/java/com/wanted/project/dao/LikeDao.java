package com.wanted.project.dao;

import com.wanted.project.core.MongoDao;
import com.wanted.project.model.Like;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDao extends MongoDao<Like> {
    @Override
    protected Class<Like> getEntityClass() {
        return Like.class;
    }
}
