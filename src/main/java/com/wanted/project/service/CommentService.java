package com.wanted.project.service;
import com.wanted.project.model.Comment;
import com.wanted.project.core.Service;
import com.wanted.project.model.VO.CommentVO;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2023/03/18.
 */
public interface CommentService extends Service<Comment> {
    Comment findById(long Id);//查

    void update(CommentVO model);//改
    //void deleteById(long Id);//删
    void addComment(CommentVO commentVO, HttpServletRequest request);//增
}
