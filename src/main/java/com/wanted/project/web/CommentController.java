package com.wanted.project.web;

import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Comment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.model.UserCommentLike;
import com.wanted.project.model.VO.CommentVO;
import com.wanted.project.service.UserService;
import com.wanted.project.service.impl.CommentServiceImpl;
import com.wanted.project.service.impl.UserCommentLikeServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by CodeGenerator on 2023/11/06.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentServiceImpl commentService;

    @Resource
    private UserService userService;

    @Resource
    private UserCommentLikeServiceImpl userCommentLikeService;

    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.save(comment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        commentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Comment comment) {
        commentService.update(comment);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/thumb")
    public Result thumb(HttpServletRequest request, @RequestParam String commentId, @RequestParam String postId) {
        Long userId = WebUtil.getCurrentId(request);
        Comment comment = commentService.findById(commentId);
        List<UserCommentLike> list = userCommentLikeService.findByCondition(UserCommentLike.builder().userId(userId).postId(postId).commentId(commentId).build());
        if (!list.isEmpty()) {
            return ResultGenerator.genFailResult("已经点赞过了");
        }
        UserCommentLike userCommentLike = UserCommentLike.builder().userId(userId).postId(postId).commentId(commentId).build();
        userCommentLikeService.save(userCommentLike);

        comment.setThumbs(comment.getThumbs() + 1);
        commentService.update(comment);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/cancel_thumb")
    public Result userCancelThumbs(HttpServletRequest request, @RequestParam String commentId, @RequestParam String postId) {
        Long userId = WebUtil.getCurrentId(request);
        List<UserCommentLike> list = userCommentLikeService.findByCondition(UserCommentLike.builder().userId(userId).postId(postId).commentId(commentId).build());
        userCommentLikeService.deleteById(list.get(0).get_id());
        Comment comment = commentService.findById(commentId);
        comment.setThumbs(comment.getThumbs() - 1);
        commentService.update(comment);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/user_thumbs")
    public Result userThumbs(HttpServletRequest request, @RequestParam String postId) {
        Long userId = WebUtil.getCurrentId(request);
        List<UserCommentLike> list = userCommentLikeService.findByCondition(UserCommentLike.builder().userId(userId).postId(postId).build());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            res.add(list.get(i).getCommentId());
        }
        return ResultGenerator.genSuccessResult(res);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        Comment comment = commentService.findById(id);
        return ResultGenerator.genSuccessResult(comment);
    }

    @GetMapping("/list_by_post")
    public Result listByPost(@RequestParam String postId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<Comment> list = commentService.findByCondition(Comment.builder().postId(postId).build());
        List<CommentVO> listVO = list.stream().map(comment ->{
            CommentVO vo = new CommentVO();
            BeanUtils.copyProperties(comment,vo);
            vo.setUsername(userService.getUsernameById(vo.getUserId()));
            if(!Objects.isNull(vo.getParentId())){
                Comment parentComment = commentService.findById(vo.getParentId());
                vo.setParentName(userService.getUsernameById(parentComment.getUserId()));
            }
            return vo;
        }).collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(listVO);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Comment> list = commentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
