package com.kadioglumf.interview.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse implements Serializable {

    @JsonProperty(value = "start")
    private String start;
    @JsonProperty(value = "numFound")
    private String numFound;
    @JsonProperty(value = "docs")
    private List<DocResponse> docs;
}
