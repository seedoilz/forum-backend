package com.wanted.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @Field("_id")
    private String _id;

    @Column(name = "user_id")
    private Long userId;

    private String content;

    private Boolean anony;

    @Column(name = "created_at")
    private Date createdAt;

    private List<String> tags;
}
