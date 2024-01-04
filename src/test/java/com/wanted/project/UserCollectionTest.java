package com.wanted.project;

import com.wanted.project.dao.UserCollectionDao;
import com.wanted.project.model.UserCollection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

@Rollback
public class UserCollectionTest extends Tester{
    @Autowired
    private UserCollectionDao userCollectionDao;

    @Test
    public void test(){
        userCollectionDao.insert(UserCollection.builder().userId(16160L).postId("77a").build());
    }
}
