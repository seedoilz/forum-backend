package com.wanted.project.service;
import com.wanted.project.core.Result;
import com.wanted.project.core.Service;
import com.wanted.project.model.Post;
import com.wanted.project.model.VO.PostVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/02/28.
 */
public interface PostService extends Service<Post> {

    Result poster(PostVO postVO);

    Result modifyPost(long id, String content);

    List<Post> findPostsByUserId(long userId);
}
