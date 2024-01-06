package com.wanted.project.service.impl;

import com.wanted.project.dao.SearchWordMapper;
import com.wanted.project.model.SearchWord;
import com.wanted.project.service.SearchWordService;
import com.wanted.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2024/01/06.
 */
@Service
@Transactional
public class SearchWordServiceImpl extends AbstractService<SearchWord> implements SearchWordService {
    @Resource
    private SearchWordMapper searchWordMapper;

    @Override
    public void insert(SearchWord searchWord){
        searchWordMapper.insertWord(searchWord);
    }
}
