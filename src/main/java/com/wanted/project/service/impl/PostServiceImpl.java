package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.dao.PostDao;
import com.wanted.project.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class PostServiceImpl extends AbstractMongoService<Post>{
    @Resource
    private PostDao postDao;

    public List<Post> getPostsByTag(String tag) {
        return postDao.getPostsByTag(tag);
    }

}
