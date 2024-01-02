package com.wanted.project.dao;
import com.wanted.project.core.MongoDao;
import com.wanted.project.core.MongoPage;
import com.wanted.project.model.Post;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class PostDao extends MongoDao<Post> {

    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }

//    public List<Post> getPostsByTag(String tag, Integer page, Integer size ){
//        Criteria criteria = Criteria.where("tags").in(tag);
//        Query query = Query.query(criteria);
//        return selectByPage(query, page, size);
//    }

    public List<Post> getPostsByTags(List<String> tags){
        Criteria criteria = Criteria.where("tags").in(tags);
        Query query = Query.query(criteria);
        return mongoTemplate.find(query, getEntityClass());
    }

}
