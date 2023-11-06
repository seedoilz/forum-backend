package com.wanted.project.service.impl;

import com.wanted.project.dao.PostMapper;
import com.wanted.project.model.Post;
import com.wanted.project.service.PostService;
import com.wanted.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/11/06.
 */
@Service
@Transactional
public class PostServiceImpl extends AbstractService<Post> implements PostService {
    @Resource
    private PostMapper postMapper;

}
