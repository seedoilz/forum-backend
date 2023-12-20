package com.wanted.project.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password_hash")
    private String passwordHash;

    private String name;

    private String email;

    @Column(name = "avatar_url")
    private String avatar_url;

    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd")   // 页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")   // 数据库导出页面时json格式化
    private Date createdAt;

    @Column(name = "ip_addr")
    private String ipAddr;

//    private Integer coin;
    @Column(name = "gender")
    private int gender;
}
