package com.wanted.project.service;
import com.wanted.project.model.SearchWord;
import com.wanted.project.core.Service;


/**
 * Created by CodeGenerator on 2024/01/06.
 */
public interface SearchWordService extends Service<SearchWord> {

    void insert(SearchWord searchWord);
}
