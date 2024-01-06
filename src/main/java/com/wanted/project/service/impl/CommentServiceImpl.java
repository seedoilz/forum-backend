package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.core.MongoPage;
import com.wanted.project.dao.CommentDao;
import com.wanted.project.model.Comment;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl extends AbstractMongoService<Comment> {

    @Resource
    private CommentDao commentDao;

    public MongoPage<Comment> findRootComments(String postId, int pageNum, int pageSize){
        Query query = Query.query(Criteria.where("postId").is(postId).and("rootId").exists(false));
        MongoPage<Comment> page = new MongoPage<>();
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        page.setTotal(commentDao.selectCountByCondition(query));
        page.setList(commentDao.selectByPage(query, pageNum, pageSize));
        return page;
    }
}
