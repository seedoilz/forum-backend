package com.wanted.project.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostVO {
    private String _id;
    private Long userId;
    private String title;
    private String content;
    private List<String> imageUrls;
    private Boolean anony;
    private Date createdAt;
    private String region;
    private List<String> tags;
    private Boolean collected;
    private String name;
    private String avatarUrl;
    private Integer collectionNum;
}
