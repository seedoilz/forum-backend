package com.wanted.project.web;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Post;
import com.wanted.project.model.VO.PostVO;
import com.wanted.project.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/02/28.
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @PostMapping("/add")
    public Result poster(@RequestBody PostVO postVO){
        postService.poster(postVO);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result deletePost(@RequestParam("id") Integer id) {
        postService.deleteById(id);
        return ResultGenerator.genSuccessResult(id);
    }

    @PostMapping("/update")
    public Result updatePost(@RequestParam("id") Integer id, @RequestParam("content") String content) {
        postService.modifyPost(id, content);
        return ResultGenerator.genSuccessResult(id);
    }

    @PostMapping("/detail")
    public Result findPostById(@RequestParam("id") Integer id) {
        Post post = postService.findById(id);
        return ResultGenerator.genSuccessResult(post);
    }

    @PostMapping("/list_by_userid")
    public Result findPostsByUserId(@RequestParam("userId") Integer userId) {
        List<Post> idList = postService.findPostsByUserId(userId);
        return ResultGenerator.genSuccessResult(idList);
    }

    @PostMapping("/list")
    public Result listAllPosts(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Post> list = postService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
