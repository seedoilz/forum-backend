package com.wanted.project.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public abstract class AbstractMongoService<T>{
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

    public void update(T model) {
        mongoDao.updateByPrimaryKey(model);
    }

    public T findOne(T obj) throws TooManyResultsException {
        return mongoDao.selectOne(obj);
    }

    public T findById(String id){
        return mongoDao.selectByPrimaryKey(id);
    }


    public List<T> findByCondition(T obj) {
        return mongoDao.select(obj);
    }

    public List<T> findAll() {
        return mongoDao.selectAll();
    }
}
