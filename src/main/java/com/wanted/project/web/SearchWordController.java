package com.wanted.project.web;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.SearchWord;
import com.wanted.project.service.SearchWordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* Created by CodeGenerator on 2024/01/06.
*/
@RestController
@RequestMapping("/search/word")
public class SearchWordController {
    @Resource
    private SearchWordService searchWordService;

    @GetMapping("/query")
    public Result query(@RequestParam String word,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size){
        PageHelper.startPage(page, size);
        Condition condition = new Condition(SearchWord.class);
        condition.createCriteria().andLike("word", "%"+word+"%");
        List<SearchWord> searchWords = searchWordService.findByCondition(condition);
        List<String> words = searchWords.stream().map(SearchWord::getWord).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(words);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/add")
    public Result add(SearchWord searchWord) {
        searchWordService.save(searchWord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        searchWordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SearchWord searchWord) {
        searchWordService.update(searchWord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SearchWord searchWord = searchWordService.findById(id);
        return ResultGenerator.genSuccessResult(searchWord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SearchWord> list = searchWordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
