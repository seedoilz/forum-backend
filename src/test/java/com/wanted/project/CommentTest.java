package com.wanted.project;


import com.wanted.project.dao.CommentDao;
import com.wanted.project.model.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@Rollback(value = false)
public class CommentTest extends Tester {
    @Autowired
    private CommentDao commentDao;

    @Test
    public void test() {
        commentDao.insert(Comment.builder()
                .content("这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容")
                .thumbs(132).userId(16160L).createdAt(new Date()).postId("77a").build());
    }
}