package com.wanted.project.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionVO {
    private String _id;
    private Long userId;
    private String postId;
    private Date createdAt;
    private String description;
    private String title;
}
