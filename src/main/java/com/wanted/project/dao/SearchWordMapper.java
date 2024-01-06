package com.wanted.project.dao;

import com.wanted.project.core.Mapper;
import com.wanted.project.model.SearchWord;

public interface SearchWordMapper extends Mapper<SearchWord> {
    int insertWord(SearchWord searchWord);
}
