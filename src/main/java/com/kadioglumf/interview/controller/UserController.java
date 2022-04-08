package com.kadioglumf.interview.controller;

import com.kadioglumf.interview.annotations.IncomingLogger;
import com.kadioglumf.interview.enums.IncomingOperationNameEnum;
import com.kadioglumf.interview.mapper.UserMapper;
import com.kadioglumf.interview.model.dto.UserDto;
import com.kadioglumf.interview.model.request.UserRequestModel;
import com.kadioglumf.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @IncomingLogger(operationName = IncomingOperationNameEnum.FIND_USER_BY_ID)
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable(name = "id") Long id) {
        UserDto response = userService.findUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @IncomingLogger(operationName = IncomingOperationNameEnum.CREATE_USER)
    @PostMapping(value = "/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestModel userRequestModel) {
        UserDto userDto = UserDto.builder()
                .firstName(userRequestModel.getFirstName())
                .lastName(userRequestModel.getLastName())
                .build();

        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @IncomingLogger(operationName = IncomingOperationNameEnum.UPDATE_USER_BOOK)
    @PutMapping(value = "/book/{book-id}")
    public ResponseEntity<UserDto> updateUserBook(@RequestBody UserRequestModel requestModel, @PathVariable("book-id") Long bookId) {
        UserDto userDto = userMapper.toUserDto(requestModel);
        UserDto responseUser = userService.updateUserBook(userDto, bookId);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);


    }

    @IncomingLogger(operationName = IncomingOperationNameEnum.CREATE_USER_BOOK)
    @PostMapping(value = "/book")
    public ResponseEntity<UserDto> createUserBook(@RequestBody UserRequestModel requestModel) {
        UserDto userDto = userMapper.toUserDto(requestModel);
        UserDto responseUser = userService.createUserBook(userDto);
        return new ResponseEntity<>(responseUser, HttpStatus.OK);
    }
}
