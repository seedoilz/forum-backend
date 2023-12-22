package com.wanted.project.dao;

import com.wanted.project.core.MongoDao;
import com.wanted.project.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao extends MongoDao<Comment> {

    @Override
    protected Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
