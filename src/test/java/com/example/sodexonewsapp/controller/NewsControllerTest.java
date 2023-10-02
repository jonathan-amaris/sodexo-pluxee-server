package com.example.sodexonewsapp.controller;

import com.example.sodexonewsapp.model.News;
import com.example.sodexonewsapp.repository.NewsRepository;
import com.example.sodexonewsapp.utilities.ConvertDataToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(NewsController.class)
class NewsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NewsRepository newsRepository;

  News news;
  List<News> newsList = new ArrayList<>();

  @BeforeEach
  void setUp() {
    news = new News(
      1,
      "How Sierra Space Protects America’s Next Space Plane, Dream Chaser",
      "Dream Chaser, the so-called “mini shuttle”, is set to bring back the capability of returning experiments.",
      "2023-09-29T18:58:06Z",
      "2023-10-01 14:54:10.219953"
    );

    newsList.add(news);
  }

  // Should find all available news
  @Test
  void testGetAllNews() throws Exception {
    when(newsRepository.getAllNewsDesc(0)).thenReturn(newsList);

    this.mockMvc
      .perform(
          get("/api/v1/news/favorites").param("offset", "0")
      )
      .andDo(print())
      .andExpect(status().isOk());
  }

  // Should find available news by matching with the Keyword 'Sierra' and check there's body return.
  @Test
  void testGetNewsBySearching() throws Exception {
    when(newsRepository.getAllNewsDesc(0)).thenReturn(newsList);

    MvcResult result = this.mockMvc
      .perform(
        get("/api/v1/news/favorites")
          .param("offset", "0")
          .param("search", "Sierra")
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    String bodyContent = result.getResponse().getContentAsString();

    assertThat(bodyContent).isNotEmpty();
  }

  // Should successfully add the news as favorites.
  @Test
  void testAddNewsToFavorite() throws Exception {
    String requestJson = ConvertDataToString.convertToJson(news);

    when(newsRepository.save(news)).thenReturn(news);

    this.mockMvc
      .perform(
        post("/api/v1/news/favorites")
          .contentType(MediaType.APPLICATION_JSON)
          .content(requestJson)
      )
      .andDo(print())
      .andExpect(status().isCreated());
  }

  // Should fail by adding a null id
  @Test
  void testAddNewsToFavoriteFailed() throws Exception {
    News copyNews = new News(
      null,
      news.getTitle(),
      news.getSummary(),
      news.getPublished_at(),
      news.getAdded_to_favorites_at()
    );

    String requestJson = ConvertDataToString.convertToJson(copyNews);

    when(newsRepository.save(news)).thenReturn(news);

    this.mockMvc
      .perform(
        post("/api/v1/news/favorites")
          .contentType(MediaType.APPLICATION_JSON)
          .content(requestJson)
      )
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  void removeNewsToFavorite() throws Exception {
    this.mockMvc.perform(delete("/api/v1/news/favorites/1"))
      .andDo(print())
      .andExpect(status().isOk());
  }
}