package com.wanted.project.dao;

import com.wanted.project.core.MongoDao;
import com.wanted.project.model.UserCollection;
import org.springframework.stereotype.Repository;

@Repository
public class UserCollectionDao extends MongoDao<UserCollection> {

    @Override
    protected Class<UserCollection> getEntityClass() {
        return UserCollection.class;
    }
}
