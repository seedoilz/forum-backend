package com.wanted.project.web;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.UserCollection;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.service.impl.UserCollectionServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* Created by CodeGenerator on 2023/12/20.
*/
@RestController
@RequestMapping("/collection")
public class UserCollectionController {
    @Resource
    private UserCollectionServiceImpl collectionService;

    @PostMapping("/add")
    public Result add(UserCollection userCollection) {
        collectionService.save(userCollection);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam String postId){
        Long userId = WebUtil.getCurrentId(request);
        String id = collectionService.findOne(UserCollection.builder().userId(userId).postId(postId).build()).get_id();
        collectionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
//        collectionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

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

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserCollection> list = collectionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
