package com.wanted.project;

import com.wanted.project.dao.PostDao;
import com.wanted.project.model.Post;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Rollback(value = false)
public class PostTest extends Tester{
    @Autowired
    private PostDao postDao;

    @Test
    public void test1(){
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg");
        List<String> tags = new ArrayList<>();
        tags.add("抽象");
        tags.add("逆天");
        Post post = Post.builder()
                .userId(0L)
                .title("尝试一下Title")
                .anony(false)
                .createdAt(new Date())
                .content("第四次发帖")
                .tags(tags)
                .imageUrls(imageUrls).build();
        postDao.insert(post);
        List<Post> res = postDao.selectAll();
        res.forEach(System.out::println);
    }

    @Test
    public void test2(){
        String tag = "无聊";
        List<Post> res = postDao.getPostsByTag(tag);
        res.forEach(System.out::println);
    }
}
