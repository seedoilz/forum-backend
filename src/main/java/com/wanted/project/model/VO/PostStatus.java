package com.wanted.project.model.VO;

import lombok.Getter;

@Getter
public enum PostStatus {
    DRAFT("草稿"),
    PENDING("待审核"),
    PUBLISHED("已发布"),
    DELETED("已删除");

    private final String description;

    PostStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}