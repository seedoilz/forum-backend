package com.wanted.project.web;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Action;
import com.wanted.project.service.impl.ActionServiceImpl;
import com.wanted.project.utils.WebUtil;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/action")
public class ActionController {
    @Resource
    ActionServiceImpl actionService;

    @GetMapping("/update")
    public Result updateState(@RequestParam String id){
        actionService.updateState(id);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/updateAll")
    public Result updateStateAll(HttpServletRequest request){
        Long userId = WebUtil.getCurrentId(request);
        actionService.updateAll(Action.builder().uid2(userId).build(), Action.builder().state(1).build());
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/message")
    public Result listMyMessage(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer state,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size){
        Long userId = WebUtil.getCurrentId(request);
        return ResultGenerator.genSuccessResult(actionService.findByPageAndCondition(Action.builder().uid2(userId).state(state).build(), page, size, new Sort(Sort.Direction.DESC, "createdAt")));
    }

    @GetMapping("/all")
    public Result listAllMessage(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size){
        Long userId = WebUtil.getCurrentId(request);
        return ResultGenerator.genSuccessResult(actionService.findByPageAndCondition(Action.builder().uid2(userId).build(), page, size, new Sort(Sort.Direction.DESC, "createdAt")));
    }
}
