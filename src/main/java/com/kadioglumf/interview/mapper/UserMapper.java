package com.kadioglumf.interview.mapper;

import com.kadioglumf.interview.entity.BookEntity;
import com.kadioglumf.interview.entity.UserEntity;
import com.kadioglumf.interview.model.dto.UserDto;
import com.kadioglumf.interview.model.request.UserRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "requestModel.bookRequestModels", target = "books")
    UserDto toUserDto(UserRequestModel requestModel);

    @Mapping(source = "entity.books", target = "books", ignore = true)
    UserDto toUserDto(UserEntity entity);

    @Mapping(source = "userDto.books", target = "books", ignore = true)
    UserEntity toUserEntity(UserDto userDto);
}
