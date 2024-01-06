package com.wanted.project.core;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Repository
public abstract class MongoDao<T>{
    @Resource
    protected MongoTemplate mongoTemplate;

    protected abstract Class<T> getEntityClass();

    public void delete(T t) {
        mongoTemplate.remove(t);
    }

    public List<T> query(Query query){
        return mongoTemplate.find(query, getEntityClass());
    }

    public List<T> selectByPage(Query query, int pageNum, int pageSize){
        int skip = (pageNum - 1) * pageSize;
        query.skip(skip);
        query.limit(pageSize);
        return mongoTemplate.find(query, getEntityClass());
    }

    public long count(){
        Query query = new Query();
        return mongoTemplate.count(query, getEntityClass());
    }

    public List<T> selectByPageAndCondition(Query query, T t, int pageNum, int pageSize){
        int skip = (pageNum - 1) * pageSize;
        query.skip(skip);
        query.limit(pageSize);

        String[] fieldNames = getFieldName(t);
        Criteria criteria = new Criteria();
        for (String fieldName : fieldNames) {
            Object fieldValue = getFieldValueByName(fieldName, t);
            if (!Objects.isNull(fieldValue)) {
                criteria.and(fieldName).is(fieldValue);
            }
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query, getEntityClass());
    }


    public void deleteByPrimaryKey(String id){
        Criteria criteria = Criteria.where("_id").is(id);
        if(!Objects.isNull(criteria)){
            Query query = Query.query(criteria);
            mongoTemplate.remove(query, getEntityClass());
//            T obj = mongoTemplate.findOne(query, getEntityClass());
//            if(!Objects.isNull(obj)){
//                delete(obj);
//            }
        }
    }

    public void deleteByCondition(T t){
        Query query = getQueryByObject(t);
        mongoTemplate.remove(query, getEntityClass());
    }

    public int insert(T t) {
        mongoTemplate.save(t);
        return 0;
    }

    public void insertList(List<T> list){
        mongoTemplate.insert(list, getEntityClass());
    }

    public T selectByPrimaryKey(String key){
        Query query = Query.query(Criteria.where("_id").is(key));
        return mongoTemplate.findOne(query, getEntityClass());
    }



    public List<T> selectAll() {
        return mongoTemplate.findAll(getEntityClass());
    }

    public List<T> select(T t) {
        Query query = getQueryByObject(t);
        return mongoTemplate.find(query, getEntityClass());
    }


    private Query getQueryByObject(T t){
        Query query = new Query();
        String[] fieldNames = getFieldName(t);
        Criteria criteria = new Criteria();
        for (String fieldName : fieldNames) {
            Object fieldValue = getFieldValueByName(fieldName, t);
            if (!Objects.isNull(fieldValue)) {
                criteria.and(fieldName).is(fieldValue);
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    private static String[] getFieldName(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; ++i){
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    private Update getUpdateByObject(T t){
        Update update = new Update();
        String[] fieldNames = getFieldName(t);
        for (String fieldName: fieldNames
             ) {
            Object fieldValue = getFieldValueByName(fieldName, t);
            if (!Objects.isNull(fieldValue)) {
                update.set(fieldName, fieldValue);
            }
        }
        return update;
    }

    private static Object getFieldValueByName(String fieldName, Object o){
        try{
            String e = fieldName.substring(0,1).toUpperCase();
            String getter = "get" + e + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        }catch (Exception ex){
            return null;
        }
    }

    public Long selectCount(T t){
        Query query = getQueryByObject(t);
        return mongoTemplate.count(query, getEntityClass());
    }

    public Long selectCountByCondition(Query query){
        return mongoTemplate.count(query, getEntityClass());
    }

    public T selectOne(T t) {
        Query query = Query.query(Criteria.byExample(Example.of(t)));
        return mongoTemplate.findOne(query, getEntityClass());
    }

    public int updateByPrimaryKey(T t) {
        String id = (String) getFieldValueByName("_id", t);
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = getUpdateByObject(t);
        return mongoTemplate.updateFirst(query, update, getEntityClass()).getN();
    }

    public int updateAll(T example, T t){
        Query query = getQueryByObject(example);
        Update update = getUpdateByObject(t);
        return mongoTemplate.updateMulti(query, update, getEntityClass()).getN();
    }

//    public int updateByPrimaryKeySelective(T t) {
//        return 0;
//    }
//
//    public int deleteByCondition(Object o) {
//        return 0;
//    }
//
//    public List<T> selectByCondition(Object o) {
//        return null;
//    }
//
//    public int selectCountByCondition(Object o) {
//        return 0;
//    }
//
//    public int updateByCondition(T t, Object o) {
//        return 0;
//    }
//
//    public int updateByConditionSelective(T t, Object o) {
//        return 0;
//    }
//
//    public int deleteByIds(String s) {
//        return 0;
//    }
//
//    public List<T> selectByIds(String s) {
//        return null;
//    }
//
//    public int insertList(List<T> list) {
//        return 0;
//    }
}
