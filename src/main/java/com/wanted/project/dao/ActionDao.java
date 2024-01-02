package com.wanted.project.dao;

import com.wanted.project.core.MongoDao;
import com.wanted.project.model.Action;
import org.springframework.stereotype.Repository;

@Repository
public class ActionDao extends MongoDao<Action> {
    @Override
    protected Class<Action> getEntityClass() {
        return Action.class;
    }
}
