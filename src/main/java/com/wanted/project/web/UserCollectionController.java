package com.wanted.project.web;

import com.wanted.project.core.MongoPage;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Post;
import com.wanted.project.model.UserCollection;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.model.VO.UserCollectionVO;
import com.wanted.project.service.impl.ActionServiceImpl;
import com.wanted.project.service.impl.PostServiceImpl;
import com.wanted.project.service.impl.UserCollectionServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by CodeGenerator on 2023/12/20.
 */
@RestController
@RequestMapping("/collection")
public class UserCollectionController {
    @Resource
    private UserCollectionServiceImpl collectionService;

    @Resource
    private PostServiceImpl postService;

    @Resource
    private ActionServiceImpl actionService;

    @PostMapping("/add")
    public Result add(HttpServletRequest httpServletRequest, @RequestBody UserCollection userCollection) {
        userCollection.setUserId(WebUtil.getCurrentId(httpServletRequest));
        userCollection.setCreatedAt(new Date());
        collectionService.save(userCollection);
        Post post = postService.findById(userCollection.getPostId());
        post.setCollectionNum(post.getCollectionNum()+1);
        postService.update(post);

        Long other =postService.findById( userCollection.getPostId()).getUserId();
        if(!Objects.equals(other, userCollection.getUserId())){
            actionService.create(1,userCollection.getUserId(),other,userCollection.getPostId());
        }

        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam String postId) {
        Long userId = WebUtil.getCurrentId(request);
        String id = collectionService.findOne(UserCollection.builder().userId(userId).postId(postId).build()).get_id();
        Post post = postService.findById(postId);
        if(!Objects.isNull(post)) {
            post.setCollectionNum(post.getCollectionNum() - 1);
            postService.update(post);
        }
        collectionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

//    @GetMapping("/delete")
//    public Result delete(HttpServletRequest request, @RequestParam String id) {
////        Long userId = WebUtil.getCurrentId(request);
////        collectionService.deleteById(id);
////        List<UserCollection> list = collectionService.findByCondition(UserCollection.builder().userId(userId).postId(postId).build());
//        collectionService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }

    @PostMapping("/update")
    public Result update(UserCollection userCollection) {
        collectionService.update(userCollection);
        return ResultGenerator.genSuccessResult();
    }

//    @PostMapping("/detail")
//    public Result detail(@RequestParam Integer id) {
//        Collection collection = collectionService.findById(id);
//        return ResultGenerator.genSuccessResult(collection);
//    }

    @GetMapping("/list_by_user")
    public Result listByUser(HttpServletRequest request) {
        Long userId = WebUtil.getCurrentId(request);
        List<UserCollection> list = collectionService.findByCondition(UserCollection.builder().userId(userId).build());
        return ResultGenerator.genSuccessResult(list);
    }

    @GetMapping("/list")
    public Result list(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Long userId = WebUtil.getCurrentId(request);
        MongoPage<UserCollection> list = collectionService.findByPageAndCondition(UserCollection.builder().userId(userId).build(), page, size);
        MongoPage<UserCollectionVO> res = new MongoPage<>();
        List<UserCollectionVO> userCollectionVOs = list.getList().stream().map(userCollection -> {
            UserCollectionVO userCollectionVO = new UserCollectionVO();
            BeanUtils.copyProperties(userCollection, userCollectionVO);
            Post post = postService.findById(userCollection.getPostId());
            if (post != null) {
                userCollectionVO.setTitle(post.getTitle());
            } else {
                userCollectionVO.setTitle("帖子已删除");
            }
            return userCollectionVO;
        }).collect(Collectors.toList());
        res.setList(userCollectionVOs);
        res.setPageNum(list.getPageNum());
        res.setPageSize(list.getPageSize());
        res.setTotal(list.getTotal());
        return ResultGenerator.genSuccessResult(res);
    }
}
