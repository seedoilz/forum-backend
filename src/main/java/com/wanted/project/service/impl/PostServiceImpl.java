package com.wanted.project.service.impl;

import com.wanted.project.core.AbstractMongoService;
import com.wanted.project.core.MongoPage;
import com.wanted.project.dao.PostDao;
import com.wanted.project.model.Post;
import com.wanted.project.model.VO.PostOption;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class PostServiceImpl extends AbstractMongoService<Post>{
    @Resource
    private PostDao postDao;

    public List<Post> getPostsByTag(String tag) {
        return postDao.getPostsByTag(tag);
    }

    public MongoPage<Post> findAllWithOptions(PostOption postOption, int pageNum, int pageSize){
        Query query = postOption.buildQuery();
        MongoPage<Post> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(mongoDao.count());
        page.setList(mongoDao.selectByPage(query, pageNum, pageSize));
        return page;
    }

}
