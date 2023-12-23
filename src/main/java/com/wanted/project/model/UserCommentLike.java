package com.wanted.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentLike {

    @Id
    @Field("_id")
    private String _id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_id")
    private String commentId;

    @Column(name = "post_id")
    private String postId;
}
