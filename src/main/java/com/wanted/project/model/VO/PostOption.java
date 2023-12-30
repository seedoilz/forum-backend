package com.wanted.project.model.VO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOption {
    private String time;
    private String sortBy;
    private String order;

    public Query buildQuery(){
        CriteriaDefinition timeCriteria;
        Sort.Direction dir = Sort.Direction.DESC;
        Sort sort = new Sort(dir,"collectionNum");
        switch (order){
            case "降序":
                break;
            case "升序":
                dir = Sort.Direction.ASC;
                break;
        }
        switch (sortBy){
            case "热度":
                sort = new Sort(dir,"collectionNum");
                break;
            case "时间":
                sort = new Sort(dir,"createdAt");
                break;
            case "评论数":
                sort = new Sort(dir,"commentNum");
                break;
        }
        LocalDateTime endTime = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startTime = endTime.minusYears(5);
        switch (time){
            case "所有时间":
                break;
            case "一天内":
                startTime = endTime.minusDays(1);
                break;
            case "一周内":
                startTime = endTime.minusDays(7);
                break;
            case "一月内":
                startTime = endTime.minusDays(30);
                break;
            case "一年内":
                startTime = endTime.minusDays(365);
                break;
        }
        timeCriteria = Criteria.where("createdAt").lte(endTime).gte(startTime);
        return Query.query(timeCriteria).with(sort);
    }
}
