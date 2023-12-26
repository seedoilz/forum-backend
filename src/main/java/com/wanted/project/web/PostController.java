package com.wanted.project.web;

import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Post;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.model.User;
import com.wanted.project.model.UserCollection;
import com.wanted.project.model.VO.PostVO;
import com.wanted.project.service.UserService;
import com.wanted.project.service.impl.PostServiceImpl;
import com.wanted.project.service.impl.UserCollectionServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostServiceImpl postService;

    @Resource
    private UserCollectionServiceImpl userCollectionService;

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(Post post) {
        postService.save(post);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        postService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Post post) {
        postService.update(post);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(HttpServletRequest request, @RequestParam String id) {
        Post post = postService.findById(id);
        Long userId = WebUtil.getCurrentId(request);
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        if (!userCollectionService.findByCondition(UserCollection.builder().userId(userId).postId(id).build()).isEmpty()) {
            postVO.setCollected(true);
        } else {
            postVO.setCollected(false);
        }
        User postUser = userService.findById(post.getUserId());
        postVO.setName(postUser.getName());
        postVO.setAvatarUrl(postUser.getAvatar_url());
        return ResultGenerator.genSuccessResult(postVO);
    }

    @GetMapping("/posts_by_tag")
    public Result postByTag(@RequestParam String tag) {
        return ResultGenerator.genSuccessResult(postService.getPostsByTag(tag));
    }

    @PostMapping("/list")
    public Result list(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<Post> list = postService.findAllByPage(page, size);
        Long userId = WebUtil.getCurrentId(request);
        List<UserCollection> userCollectionList = userCollectionService.findByCondition(UserCollection.builder().userId(userId).build());
        List<String> extractedList = userCollectionList.stream().map(UserCollection::getPostId).collect(Collectors.toList());

        List<PostVO> postVOS = list.stream().map(post -> {
            // 添加当前用户是否收藏了这条帖子
            PostVO postVO = new PostVO();
            BeanUtils.copyProperties(post, postVO);
            if (extractedList.contains(post.get_id())) {
                postVO.setCollected(true);
            } else {
                postVO.setCollected(false);
            }
            // 添加当前帖子的点赞数量
            postVO.setCollectionNum(userCollectionService.findByCondition(UserCollection.builder().postId(post.get_id()).build()).size());
            // 获得当前帖子的作者信息
            User postUser = userService.findById(post.getUserId());
            postVO.setName(postUser.getName());
            postVO.setAvatarUrl(postUser.getAvatar_url());

            return postVO;
        }).collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(postVOS);
    }
}
