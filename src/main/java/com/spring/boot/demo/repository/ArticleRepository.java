package com.spring.boot.demo.repository;

import com.spring.boot.demo.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByUserId(Long id);

    Page<Article> findByStatus(Boolean status, Pageable p);


}
