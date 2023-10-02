package com.example.sodexonewsapp.repository;

import com.example.sodexonewsapp.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value="SELECT * FROM News as n ORDER BY n.added_to_favorites_at", nativeQuery = true)
    List<News> getNews(@Param(value = "offset") Integer offset);
}
