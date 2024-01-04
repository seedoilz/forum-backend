package com.wanted.project.web;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.User;
import com.wanted.project.model.VO.UserVO;
import com.wanted.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanted.project.utils.WebUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* Created by CodeGenerator on 2023/02/28.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody UserVO userVO, HttpServletRequest request){
//        userService.register(userVO, request);
        return ResultGenerator.genFailResult("暂时关闭注册~");
//        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/sign_in")
    public Result signIn(@RequestBody UserVO userVO){
        return userService.signIn(userVO);
    }


    @PreAuthorize("hasAuthority('any')")
    @GetMapping("/sign_out")
    public Result signOut(){
        return userService.signOut();
    }


    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Long id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping("/get_current_user")
    public Result getCurrentUser(HttpServletRequest httpServletRequest){
        Long id = WebUtil.getCurrentId(httpServletRequest);
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
