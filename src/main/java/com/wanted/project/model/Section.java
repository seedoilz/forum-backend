package com.wanted.project.model;

import javax.persistence.*;

public class Section {
    @Id
    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "section_name")
    private String sectionName;

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

    /**
     * @return section_name
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param sectionName
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}