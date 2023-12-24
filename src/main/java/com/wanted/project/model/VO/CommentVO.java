package com.wanted.project.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private String _id;
    private Long userId;
    private String username;
    private String parentId;
    private String parentName;
    private String avatarUrl;
    private String postId;
    private String content;
    private Date createdAt;
    private Integer thumbs;
}
