package com.spring.boot.demo.dto;

import lombok.*;

import java.time.LocalDate;


@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private Long articleId;
    private Long userId;
    private String content;
    private String name;
    private LocalDate createdAt;
    private Boolean status;
    private String imagePath;


}
