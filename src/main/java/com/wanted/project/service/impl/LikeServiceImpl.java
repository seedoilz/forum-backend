package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractService;
import com.wanted.project.model.Like;
import com.wanted.project.service.LikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author seedoilz
* @description 针对表【like】的数据库操作Service实现
* @createDate 2023-12-20 19:38:22
*/
@Service
@Transactional
public class LikeServiceImpl extends AbstractService<Like> implements LikeService {

}




