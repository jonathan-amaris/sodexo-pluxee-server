package com.example.sodexonewsapp.utilities;

import com.example.sodexonewsapp.model.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ConvertDataToStringTest {

  // Should return a string value
  @Test
  void convertToJson() throws JsonProcessingException {
    News news = new News(
        1,
        "Title",
        "Summary",
        "published_at",
        "added_at"
    );

    String text = ConvertDataToString.convertToJson(news);

    assertThat(text).isNotEmpty();
    assertThat(text).hasSizeGreaterThan(1);
  }
}