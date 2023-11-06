package com.wanted.project.model;

import java.util.Date;
import javax.persistence.*;

public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String content;

    private Boolean anony;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "section_id")
    private Integer sectionId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return anony
     */
    public Boolean getAnony() {
        return anony;
    }

    /**
     * @param anony
     */
    public void setAnony(Boolean anony) {
        this.anony = anony;
    }

    /**
     * @return created_at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return section_id
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}