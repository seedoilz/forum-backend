package com.wanted.project;

import com.wanted.project.dao.LikeDao;
import com.wanted.project.model.Like;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
public class LikeTest extends Tester{
    @Autowired
    private LikeDao likeDao;

    @Test
    public void test(){
        likeDao.insert(Like.builder().userId(16160L).postId("77a").build());
    }
}
