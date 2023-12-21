package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.dao.LikeDao;
import com.wanted.project.model.Like;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/12/20.
 */
@Service
@Transactional
public class LikeServiceImpl extends AbstractMongoService<Like> {
    @Resource
    private LikeDao likeDao;
//    public Long findLike(Long userId, String postId){
//        return likeMapper.findLike(userId, postId).getId();
//    }
}
