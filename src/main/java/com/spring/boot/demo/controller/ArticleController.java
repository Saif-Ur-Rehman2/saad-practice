package com.spring.boot.demo.controller;


import com.spring.boot.demo.model.Article;
import com.spring.boot.demo.response.Message;
import com.spring.boot.demo.service.ArticleService;
import com.spring.boot.demo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/post")
    public ResponseEntity<Message<String>> post(@RequestBody Article article){
        return ResponseEntity.ok(this.articleService.post(article));
    }

    @GetMapping("/all-article")
    public ResponseEntity<Message<List<Article>>> getArticle(){
        return ResponseEntity.ok(this.articleService.getArticle());
    }

    @PutMapping("/status")
    public ResponseEntity<Message<Article>> deactivate(@RequestParam Boolean status, @RequestParam Long articleId, @RequestParam Long userId){
        return ResponseEntity.ok(this.articleService.changeStatus(status, articleId, userId));
    }

    @GetMapping("/user-articles/{id}")
    public ResponseEntity<Message<List<Article>>> getUserArticle(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.articleService.getUserArticle(id));
    }

}
