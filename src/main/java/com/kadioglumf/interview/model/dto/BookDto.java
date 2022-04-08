package com.kadioglumf.interview.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {
    private String title;
    private List<String> editionKey;
    private String firstPublishYear;
    private List<String> firstSentence;
    private List<String> authorKey;
    private List<String> authorName;
}
