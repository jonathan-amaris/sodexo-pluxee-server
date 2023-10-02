package com.example.sodexonewsapp.controller;

import com.example.sodexonewsapp.model.News;
import com.example.sodexonewsapp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/news")
@CrossOrigin("*")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public ResponseEntity<?> getNews(
            @RequestParam("offset") Integer offset,
            @RequestParam("ordering") Optional<String> ordering,
            @RequestParam("search") Optional<String> search
    ) {
        try {
            List<News> newsList;

            if (search.isPresent()) {
                if (ordering.isPresent() && ordering.get().equals("added_to_favorites_at")) {
                    newsList = new ArrayList<>(newsRepository.getNewsBySearchAsc(search.get(), offset));
                } else {
                    newsList = new ArrayList<>(newsRepository.getNewsBySearchDesc(search.get(), offset));
                }
            } else {
                if (ordering.isPresent() && ordering.get().equals("added_to_favorites_at")) {
                    newsList = new ArrayList<>(newsRepository.getAllNewsAsc(offset));
                } else {
                    newsList = new ArrayList<>(newsRepository.getAllNewsDesc(offset));
                }
            }

            if (newsList.isEmpty()) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }

            return new ResponseEntity<>(newsList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/favorites")
    public ResponseEntity<News> addNewsToFavorite(@RequestBody News news){
        try {
            if (news.getId() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Optional<News> newsInFavorites = newsRepository.findById(news.getId());

            if (newsInFavorites.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            News newsObj = newsRepository.save(news);

            return new ResponseEntity<>(newsObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<HttpStatus> removeNewsToFavorite(@PathVariable Integer id){
        try {
            newsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}