package com.wanted.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "search_word")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private Integer weight;
}
