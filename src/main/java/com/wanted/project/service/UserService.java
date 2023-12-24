package com.wanted.project.service;
import com.wanted.project.core.Result;
import com.wanted.project.core.Service;
import com.wanted.project.model.User;
import com.wanted.project.model.VO.UserVO;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2023/02/28.
 */
public interface UserService extends Service<User> {

    String getUsernameById(Long id);

    void register(UserVO userVO, HttpServletRequest request);

    Result signIn(UserVO userVO);

    Result signOut();

    void updateUserAvatar(Long id, String avatarUrl);

    User findUserByName(String name);
}
