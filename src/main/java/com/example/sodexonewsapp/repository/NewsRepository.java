package com.example.sodexonewsapp.repository;

import com.example.sodexonewsapp.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "SELECT * FROM News AS n ORDER BY n.added_to_favorites_at ASC LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<News> getAllNewsAsc(Integer offset);

    @Query(value = "SELECT * FROM News AS n ORDER BY n.added_to_favorites_at DESC LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<News> getAllNewsDesc(Integer offset);

    @Query(value = "SELECT * FROM News AS n WHERE n.title LIKE %?1% ORDER BY n.added_to_favorites_at ASC LIMIT 10 OFFSET ?2", nativeQuery = true)
    List<News> getNewsBySearchAsc(String search, Integer offset);

    @Query(value = "SELECT * FROM News AS n WHERE n.title LIKE %?1% ORDER BY n.added_to_favorites_at DESC LIMIT 10 OFFSET ?2", nativeQuery = true)
    List<News> getNewsBySearchDesc(String search, Integer offset);
}
