package com.wanted.project.service.impl;

import com.wanted.project.dao.PermMapper;
import com.wanted.project.dao.UserMapper;
import com.wanted.project.model.User;
import com.wanted.project.model.VO.UserStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PermMapper permMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //（认证，即校验该用户是否存在）查询用户信息
        User user = User.builder()
                .name(name)
                .build();
        User res = userMapper.selectOne(user);
        //如果没有查询到用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }


        //(授权，即查询用户具有哪些权限)查询对应的用户信息
        List<String> perms = permMapper.findPermByUserId(res.getId());

        //把数据封装成UserDetails返回
        return new UserStatus(res,perms);
    }
}
