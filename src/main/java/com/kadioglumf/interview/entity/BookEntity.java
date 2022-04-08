package com.kadioglumf.interview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "first_publish_year")
    private String firstPublishYear;

    @Column(name = "first_sentence")
    private String firstSentence;

    @Column(name = "author_key")
    private String authorKey;

    @Column(name = "author_name")
    private String authorName;
}
