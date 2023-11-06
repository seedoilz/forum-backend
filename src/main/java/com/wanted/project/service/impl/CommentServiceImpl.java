package com.wanted.project.service.impl;

import com.wanted.project.core.ServiceException;
import com.wanted.project.dao.CommentMapper;
import com.wanted.project.model.Comment;
import com.wanted.project.model.User;
import com.wanted.project.model.VO.CommentVO;
import com.wanted.project.service.CommentService;
import com.wanted.project.core.AbstractService;
import com.wanted.project.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Created by CodeGenerator on 2023/03/18.
 */
@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public void update(CommentVO model){

        Comment commentUpDate = Comment.builder()
                .id(model.getId())
                .userId(null)
                .postId(null)
                .parentId(null)
                .content(model.getContent())
                .createdAt(null)
                .modifiedAt(new Date())
                .build();
        commentMapper.updateByPrimaryKeySelective(commentUpDate);
    }

    @Override
    public void addComment(CommentVO commentVO,HttpServletRequest request) {
        Comment commentP0 = Comment.builder()
                .userId(commentVO.getUserId())
                .postId(commentVO.getPostId())
                .parentId(commentVO.getParentId())
                .content(commentVO.getContent())
                .createdAt(new Date())
                .modifiedAt(new Date())
                .build();
//        commentMapper.createComment(commentP0);
        save(commentP0);
    }
}
