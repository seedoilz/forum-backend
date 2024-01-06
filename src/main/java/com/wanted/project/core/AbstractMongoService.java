package com.wanted.project.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public abstract class AbstractMongoService<T> {
    @Autowired
    protected MongoDao<T> mongoDao;

    public void save(T model) {
        mongoDao.insert(model);
    }

    public void save(List<T> models) {
        mongoDao.insertList(models);
    }


    public void deleteById(String ids) {
        mongoDao.deleteByPrimaryKey(ids);
    }

    public void deleteAll(T model){mongoDao.deleteByCondition(model);}

    public void update(T model) {
        mongoDao.updateByPrimaryKey(model);
    }

    public void updateAll(T example, T model){
        mongoDao.updateAll(example, model);
    }

    public T findOne(T obj) throws TooManyResultsException {
        return mongoDao.selectOne(obj);
    }

    public T findById(String id) {
        return mongoDao.selectByPrimaryKey(id);
    }


    public List<T> findByCondition(T obj) {
        return mongoDao.select(obj);
    }

    public List<T> findAll() {
        return mongoDao.selectAll();
    }

    public MongoPage<T> findAllByPage(int pageNum, int pageSize) {
        Query query = new Query();
        MongoPage<T> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(mongoDao.count());
        page.setList(mongoDao.selectByPage(query, pageNum, pageSize));
        return page;
    }

    public MongoPage<T> findByPageAndCondition(T obj, int pageNum, int pageSize) {
        Query query = new Query();
        MongoPage<T> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(mongoDao.selectCount(obj));
        page.setList(mongoDao.selectByPageAndCondition(query, obj, pageNum, pageSize));
        return page;
    }

    public MongoPage<T> findByPageAndCondition(T obj, int pageNum, int pageSize, Sort sort) {
        Query query = (new Query()).with(sort);
        MongoPage<T> page = new MongoPage<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(mongoDao.selectCount(obj));
        page.setList(mongoDao.selectByPageAndCondition(query, obj, pageNum, pageSize));
        return page;
    }
}
