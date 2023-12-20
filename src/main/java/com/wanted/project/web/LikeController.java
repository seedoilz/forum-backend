package com.wanted.project.web;

import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Like;
import com.wanted.project.service.LikeService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Resource
    private LikeService likeService;

    @PostMapping("/add")
    public Result add(@RequestBody Like like){
        likeService.save(like);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam Long postId){
//        Long userId = WebUtil.getCurrentId(request);
        Condition condition = new Condition(Like.class);
        //hospital_area为要搜索的数据表字段名
        condition.createCriteria().andCondition("user_id"+10160L);
        condition.createCriteria().andCondition("post_id"+2L);
        List<Like> list = likeService.findByCondition(condition);
        likeService.deleteById(list.get(0).getId());
        return ResultGenerator.genSuccessResult();
    }
}
