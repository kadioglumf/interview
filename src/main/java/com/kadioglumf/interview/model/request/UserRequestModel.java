package com.kadioglumf.interview.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserRequestModel {
    private Long id;
    private String firstName;
    private String lastName;
    private List<BookRequestModel> bookRequestModels;
}
