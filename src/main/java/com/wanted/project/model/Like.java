package com.wanted.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    @Id
    @Field("_id")
    private String _id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "created_at")
    private Date createdAt;

}