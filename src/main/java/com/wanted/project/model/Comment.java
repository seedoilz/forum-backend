package com.wanted.project.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "parent_id")
    private Long parentId;

    private String content;

    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd")   // 页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")   // 数据库导出页面时json格式化
    private Date createdAt;

    @Column(name = "modified_at")
    @DateTimeFormat(pattern="yyyy-MM-dd")   // 页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")   // 数据库导出页面时json格式化
    private Date modifiedAt;
}
