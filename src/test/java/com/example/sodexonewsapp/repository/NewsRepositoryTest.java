package com.example.sodexonewsapp.repository;

import com.example.sodexonewsapp.model.News;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NewsRepositoryTest {
  @Autowired
  private NewsRepository newsRepository;
  News news;

  @BeforeEach
  void setUp() throws InterruptedException {
    news = new News(
        1,
        "How Sierra Space Protects America’s Next Space Plane, Dream Chaser",
        "Dream Chaser, the so-called “mini shuttle”, is set to bring back the capability of returning experiments and equipment from the International Space Station (ISS) through Earth’s atmosphere for an eventual runway landing.",
        "2023-09-29T18:58:06Z",
        "2023-10-01 14:54:10.219953"
    );

    newsRepository.save(news);
  }

  @AfterEach
  void tearDown() {
    news = null;
    newsRepository.deleteAll();
  }

  // Should find a list of News having one article.
  @Test
  void getAllNewsAsc() {
    List<News> newsList = newsRepository.getAllNewsAsc(0);

    assertThat(newsList.size()).isEqualTo(1);

    assertThat(newsList.get(0).getId()).isEqualTo(news.getId());
    assertThat(newsList.get(0).getTitle()).isEqualTo(news.getTitle());
    assertThat(newsList.get(0).getSummary()).isEqualTo(news.getSummary());
  }

  // Should not find any news by the Keyword 'Saludos';
  @Test
  void getNewsBySearchAsc() {
    List<News> newsList = newsRepository.getNewsBySearchAsc("Saludos", 0);

    assertThat(newsList.isEmpty()).isTrue();
    assertThat(newsList.size()).isEqualTo(0);
  }

  // Should find the only news by the keyword 'Space' in its ´title´
  @Test
  void getNewsBySearchDesc() {
    List<News> newsList = newsRepository.getNewsBySearchDesc("Space", 0);

    assertThat(newsList.size()).isEqualTo(1);
    assertThat(newsList.isEmpty()).isFalse();
  }
}