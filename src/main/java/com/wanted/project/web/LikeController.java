package com.wanted.project.web;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Like;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.service.impl.ActionServiceImpl;
import com.wanted.project.service.impl.LikeServiceImpl;
import com.wanted.project.service.impl.PostServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
* Created by CodeGenerator on 2023/12/20.
*/
@RestController
@RequestMapping("/like")
public class LikeController {
    @Resource
    private LikeServiceImpl likeService;

    @Resource
    private ActionServiceImpl actionService;

    @Resource
    private PostServiceImpl postService;

    @PostMapping("/add")
    public Result add(Like like) {
        likeService.save(like);
        Long other =postService.findById(like.getPostId()).getUserId();
        if(!Objects.equals(other, like.getUserId())){
            actionService.create(0,like.getUserId(),other,like.getPostId());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam String postId){
        Long userId = WebUtil.getCurrentId(request);
        String id = likeService.findOne(Like.builder().userId(userId).postId(postId).build()).get_id();
        likeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
//        likeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Like like) {
        likeService.update(like);
        return ResultGenerator.genSuccessResult();
    }

//    @PostMapping("/detail")
//    public Result detail(@RequestParam Integer id) {
//        Like like = likeService.findById(id);
//        return ResultGenerator.genSuccessResult(like);
//    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Like> list = likeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
