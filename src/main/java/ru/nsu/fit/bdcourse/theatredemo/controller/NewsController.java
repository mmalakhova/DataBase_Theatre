package ru.nsu.fit.bdcourse.theatredemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.bdcourse.theatredemo.model.NewsData;
import ru.nsu.fit.bdcourse.theatredemo.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/theatre")
@CrossOrigin("*")
public class NewsController {
    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewsData>> getAllNews() {
        List<NewsData> news = new ArrayList<>(newsRepository.getAllData());
        if (news.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

}
