package com.wanted.project.web;

import com.wanted.project.core.MongoDao;
import com.wanted.project.core.MongoPage;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.model.VO.PostOption;
import com.wanted.project.model.VO.PostVO;
import com.wanted.project.model.VO.UserStatus;
import com.wanted.project.service.SearchWordService;
import com.wanted.project.service.UserService;
import com.wanted.project.service.impl.ActionServiceImpl;
import com.wanted.project.service.impl.CommentServiceImpl;
import com.wanted.project.service.impl.PostServiceImpl;
import com.wanted.project.service.impl.UserCollectionServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostServiceImpl postService;

    @Resource
    private SearchWordService searchWordService;

    @Resource
    private CommentServiceImpl commentService;

    @Resource
    private UserCollectionServiceImpl userCollectionService;

    @Resource
    private UserService userService;

    @Resource
    private ActionServiceImpl actionService;

    @PostMapping("/add")
    public Result add(HttpServletRequest request,@RequestBody Post post) {
        post.setUserId(WebUtil.getCurrentId(request));
        post.setCreatedAt(new Date());
        post.setCollectionNum(0);
        post.setCommentNum(0);
        postService.save(post);
        searchWordService.insert(SearchWord.builder().word(post.getTitle()).build());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(HttpServletRequest request, @RequestParam String postId) {
        long userId = WebUtil.getCurrentId(request);
        Post post = postService.findById(postId);
        if(post.getUserId().equals(userId) || WebUtil.hasPermission("admin")){
            actionService.create(3,userId, post.getUserId(),postId);
            postService.deleteById(postId);
            commentService.deleteAll(Comment.builder().postId(postId).build());
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("有没有可能你没有权限？");
        }

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

    @PostMapping("/posts_by_tag")
    public Result postByTag(HttpServletRequest request, @RequestBody PostOption postOption, @RequestParam String tag, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        MongoPage<Post> pagePO = postService.getPostsByTag(postOption, tag, page, size);
        MongoPage<PostVO> pageVO = new MongoPage<>();
        pageVO.setPageNum(pagePO.getPageNum());
        pageVO.setPageSize(pagePO.getPageSize());
        pageVO.setTotal(pagePO.getTotal());
        List<Post> list = pagePO.getList();
        pageVO.setList(buildPostVO(request, list));
        return ResultGenerator.genSuccessResult(pageVO);
    }

    @PostMapping("/posts_search")
    public Result postSearch(HttpServletRequest request, @RequestBody PostOption postOption,@RequestParam String word, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        MongoPage<Post> pagePO = postService.getPostsByWord(postOption, word, page, size);
        MongoPage<PostVO> pageVO = new MongoPage<>();
        pageVO.setPageNum(pagePO.getPageNum());
        pageVO.setPageSize(pagePO.getPageSize());
        pageVO.setTotal(pagePO.getTotal());
        List<Post> list = pagePO.getList();
        pageVO.setList(buildPostVO(request, list));
        return ResultGenerator.genSuccessResult(pageVO);
    }

    private List<PostVO> buildPostVO(HttpServletRequest request, List<Post> list) {
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
//            postVO.setCollectionNum(userCollectionService.findByCondition(UserCollection.builder().postId(post.get_id()).build()).size());
            // 获得当前帖子的作者信息
            User postUser = userService.findById(post.getUserId());
            postVO.setName(postUser.getName());
            postVO.setAvatarUrl(postUser.getAvatar_url());
            return postVO;
        }).collect(Collectors.toList());
        return postVOS;
    }

    @PostMapping("/list")
    public Result list(HttpServletRequest request, @RequestBody PostOption postOption, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<Post> list = postService.findAllWithOptions(postOption, page, size).getList();
        return ResultGenerator.genSuccessResult(buildPostVO(request, list));
    }
}
