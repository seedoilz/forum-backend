package com.wanted.project.service.impl;

import com.wanted.project.dao.SectionMapper;
import com.wanted.project.model.Section;
import com.wanted.project.service.SectionService;
import com.wanted.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/11/06.
 */
@Service
@Transactional
public class SectionServiceImpl extends AbstractService<Section> implements SectionService {
    @Resource
    private SectionMapper sectionMapper;

}
