package com.kadioglumf.interview.service;

import com.kadioglumf.interview.model.dto.UserDto;

public interface UserService {

    UserDto findUserById(Long id);
    void createUser(UserDto userDto);
    UserDto updateUserBook(UserDto userDto, Long bookId);
    UserDto createUserBook(UserDto userDto);
}
