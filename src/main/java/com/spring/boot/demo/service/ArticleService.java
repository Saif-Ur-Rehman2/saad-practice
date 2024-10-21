package com.spring.boot.demo.service;


import com.spring.boot.demo.exception.BadRequestException;
import com.spring.boot.demo.exception.EntityNotFoundException;
import com.spring.boot.demo.model.Article;
import com.spring.boot.demo.model.User;
import com.spring.boot.demo.repository.ArticleRepository;
import com.spring.boot.demo.response.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserService userService;


    public Message<String> post(Article article) {

        if(Objects.isNull(article.getUser().getId())){
            throw new BadRequestException("User id is null");
        }

        User user = this.userService.findByIdAndStatus(article.getUser().getId(), true);
        if(Objects.nonNull(user)){
            article.setCreatedAt(LocalDate.now());
            article.setUser(user);
            article.setStatus(true);
            this.articleRepository.save(article);

            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("Article posted");
            message.setData("Article Posted");
            return message;
        }

        throw new EntityNotFoundException("User Not Found, Article cannot be posted against user id: "+article.getUser().getId());
    }

    public Message<List<Article>> getArticle() {

        List<Article> list = this.articleRepository.findByStatus(true);
        if(!list.isEmpty()){
            Message message = new Message();
            message.setCode(HttpStatus.OK.value());
            message.setStatus(HttpStatus.OK.name());
            message.setMessage("All Activated Articles Fetched");
            message.setData(list);
            return message;
        }
        throw new EntityNotFoundException("Articles not found.");
    }


    public Message<Article> changeStatus(Boolean status, Long articleId, Long userId) {

        User user = this.userService.findByIdAndStatus(userId, true);

        if(Objects.nonNull(user)){
            Article article = this.articleRepository.findById(articleId).orElseThrow(()->
                    new EntityNotFoundException("Article not found"));
            if(article.getUser().getId().equals(user.getId())){
                article.setStatus(status);
                this.articleRepository.save(article);
                Message message = new Message();
                message.setCode(HttpStatus.OK.value());
                message.setStatus(HttpStatus.OK.name());
                message.setMessage("Article Deactivated");
                message.setData("Article Deactivated");
                return message;
            }else{
                throw new BadRequestException("UnAuthorized Access.");
            }
        }

        throw new EntityNotFoundException("User Not Found: "+userId);
    }

    public Message<List<Article>> getUserArticle(Long id) {
        User user = this.userService.findByIdAndStatus(id, true);
        if(Objects.nonNull(user)){
            List<Article> article = this.articleRepository.findByUserId(id);
            if(!article.isEmpty()){
                Message message = new Message();
                message.setCode(HttpStatus.OK.value());
                message.setStatus(HttpStatus.OK.name());
                message.setMessage("Article Fetched");
                message.setData(article);
                return message;
        }else{
                throw new EntityNotFoundException("No Articles Posted By User");
            }

        }
            throw new EntityNotFoundException("User Not Found");

    }
}
