package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.dao.UserCommentLikeDao;
import com.wanted.project.model.UserCommentLike;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserCommentLikeServiceImpl extends AbstractMongoService<UserCommentLike> {

    @Resource
    private UserCommentLikeDao userCommentLikeDao;
}
