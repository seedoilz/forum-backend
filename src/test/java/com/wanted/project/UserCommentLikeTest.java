package com.wanted.project;

import com.wanted.project.dao.UserCommentLikeDao;
import com.wanted.project.model.UserCommentLike;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

@Rollback
public class UserCommentLikeTest extends Tester{

    @Autowired
    private UserCommentLikeDao userCommentLikeDao;

    @Test
    public void test(){
        userCommentLikeDao.insert(UserCommentLike.builder()
                .userId(16160L).commentId("77a").postId("132367812a")
                .build());
    }
}
