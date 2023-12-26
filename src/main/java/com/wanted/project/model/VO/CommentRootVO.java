package com.wanted.project.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRootVO {
    private String _id;
    private Long userId;
    private String username;
    private String avatarUrl;
    private String postId;
    private List<CommentVO> subComments;
    private String content;
    private Date createdAt;
    private Integer thumbs;
}
