package com.kadioglumf.interview.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestModel {
    private String title;
    private List<String> editionKey;
    private String firstPublishYear;
    private List<String> firstSentence;
    private List<String> authorKey;
    private List<String> authorName;
}
