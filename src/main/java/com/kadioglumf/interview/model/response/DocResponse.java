package com.kadioglumf.interview.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DocResponse implements Serializable {

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "edition_key")
    private List<String> editionKey;

    @JsonProperty(value = "first_publish_year")
    private String firstPublishYear;

    @JsonProperty(value = "first_sentence")
    private List<String> firstSentence;

    @JsonProperty(value = "author_key")
    private List<String> authorKey;

    @JsonProperty(value = "author_name")
    private List<String> authorName;
}
