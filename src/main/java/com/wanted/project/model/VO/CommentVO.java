package com.wanted.project.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    private Long Id;
    private Long postId;
    private Long userId;
    private Long parentId;
    private String content;
}
