package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.dao.UserCollectionDao;
import com.wanted.project.model.UserCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserCollectionServiceImpl extends AbstractMongoService<UserCollection> {

    @Resource
    private UserCollectionDao userCollectionDao;
}
