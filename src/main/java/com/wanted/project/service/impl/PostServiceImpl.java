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
import java.util.regex.Pattern;


@Service
@Transactional
public class PostServiceImpl extends AbstractMongoService<Post>{
    @Resource
    private PostDao postDao;

    public MongoPage<Post> getPostsByTag(PostOption postOption, String tag, int pageNum, int pageSize) {
        Criteria criteria = Criteria.where("tags").in(tag);
        Query query = Query.query(criteria).addCriteria(postOption.getTimeCriteria()).with(postOption.getSort());
        MongoPage<Post> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(postDao.selectCountByCondition(query));
        page.setList(postDao.selectByPage(query, pageNum, pageSize));
        return page;
    }

    public MongoPage<Post> getPostsByWord(PostOption postOption, String word, int pageNum, int pageSize) {
        Criteria criteria = Criteria.where("title").regex(Pattern.compile("^.*" + word + ".*$", Pattern.CASE_INSENSITIVE));
        Query query = Query.query(criteria).addCriteria(postOption.getTimeCriteria()).with(postOption.getSort());
        MongoPage<Post> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(postDao.selectCountByCondition(query));
        page.setList(postDao.selectByPage(query, pageNum, pageSize));
        return page;
    }

    public MongoPage<Post> findAllWithOptions(PostOption postOption, int pageNum, int pageSize){
        Query query = postOption.buildQuery();
        MongoPage<Post> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(postDao.count());
        page.setList(postDao.selectByPage(query, pageNum, pageSize));
        return page;
    }

}
