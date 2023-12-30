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

    private String title;

    private String content;

    private List<String> imageUrls;

    private Boolean anony;
    @Column(name = "collection_num")
    private Integer collectionNum;
    @Column(name = "comment_num")
    private Integer commentNum;
    @Column(name = "created_at")
    private Date createdAt;

    private List<String> tags;
}
