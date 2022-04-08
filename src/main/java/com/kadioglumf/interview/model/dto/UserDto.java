package com.kadioglumf.interview.model.dto;

import com.kadioglumf.interview.entity.BookEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<BookDto> books;
}
