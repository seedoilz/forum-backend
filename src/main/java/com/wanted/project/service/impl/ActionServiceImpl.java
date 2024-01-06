package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.dao.ActionDao;
import com.wanted.project.dao.PostDao;
import com.wanted.project.dao.UserMapper;
import com.wanted.project.model.Action;
import com.wanted.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class ActionServiceImpl extends AbstractMongoService<Action> {
    @Resource
    ActionDao actionDao;

    @Resource
    private UserMapper userMapper;

    @Resource
    PostDao postDao;

    public void create(int type, long uid1, long uid2, String postId){
        String opName = "";
        switch (type){
            case 0:
                opName="点赞";
                break;
            case 1:
                opName="收藏";
                break;
            case 2:
                opName="评论";
                break;
            case 3:
                opName="删除";
                break;
        }
        actionDao.insert(Action.builder()
                .opType(type).opName(opName)
                .uid1(uid1).name1(userMapper.findUserById(uid1).getName())
                .uid2(uid2).name2(userMapper.findUserById(uid2).getName())
                .state(0)
                .targetId(postId)
                .postName(postDao.selectByPrimaryKey(postId).getTitle())
                .createdAt(new Date())
                .build());
    }

    public void updateState(String id){
        Action action = actionDao.selectByPrimaryKey(id);
        action.setState(1);
        actionDao.updateByPrimaryKey(action);
    }
}
