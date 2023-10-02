package com.example.sodexonewsapp.model;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="News")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class News {
    @Id
    private Long id;

    private String title;

    @Column(name="summary", length = 1024)
    private String summary;

    private String published_at;

    @CreationTimestamp
    private String added_to_favorites_at;
}