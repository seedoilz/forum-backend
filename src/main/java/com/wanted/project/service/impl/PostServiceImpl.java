package com.wanted.project.service.impl;

import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.dao.PostMapper;
import com.wanted.project.model.Post;
import com.wanted.project.model.VO.PostVO;
import com.wanted.project.service.PostService;
import com.wanted.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2023/04/02.
 */
@Service
@Transactional
public class PostServiceImpl extends AbstractService<Post> implements PostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public Result poster(PostVO postVO){
        Post postPO = Post.builder()
                .userId(postVO.getUserId())
                .content(postVO.getContent())
                .anony(false)
                .createdAt(new Date())
                .build();
        postMapper.createPost(postPO);
        return ResultGenerator.genSuccessResult("发帖成功");
    }

    @Override
    public Result modifyPost(long id, String content) {
        Post postPO = Post.builder()
                .id(id)
                .userId(null)
                .content(content)
                .anony(null)
                .createdAt(new Date())
                .build();
        postMapper.updatePost(postPO);
        return ResultGenerator.genSuccessResult("编辑成功");
    }

    @Override
    public List<Post> findPostsByUserId(long userId) {
        return postMapper.findPostsByUserId(userId);
    }
}
