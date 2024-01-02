package com.wanted.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    @Id
    @Field("_id")
    private String _id;
    @Column(name = "uid_1")
    private Long uid1;
    @Column(name = "uid_2")
    private Long uid2;
    @Column(name = "name_1")
    private String name1;
    @Column(name = "name_2")
    private String name2;
    //0 like, 1 collect, 2 comment
    @Column(name = "op_type")
    private Integer opType;
    @Column(name = "op_name")
    private String opName;
    //post Id
    @Column(name = "target_id")
    private String targetId;
    @Column(name = "post_name")
    private String postName;
    //0 for unread, 1 for read
    private Integer state;

}
