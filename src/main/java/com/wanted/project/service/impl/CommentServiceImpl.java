package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.dao.CommentDao;
import com.wanted.project.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CommentServiceImpl extends AbstractMongoService<Comment> {

    @Resource
    private CommentDao commentDao;
}
