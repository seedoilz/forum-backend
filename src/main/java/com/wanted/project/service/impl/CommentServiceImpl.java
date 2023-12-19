package com.wanted.project.service.impl;

import com.wanted.project.dao.CommentMapper;
import com.wanted.project.model.Comment;
import com.wanted.project.service.CommentService;
import com.wanted.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;



@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

}
