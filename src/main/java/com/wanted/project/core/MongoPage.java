package com.wanted.project.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MongoPage <T>{
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

    private List<T> list;
}
