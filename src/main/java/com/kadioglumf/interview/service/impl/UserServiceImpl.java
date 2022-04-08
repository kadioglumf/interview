package com.kadioglumf.interview.service.impl;

import com.kadioglumf.interview.entity.BookEntity;
import com.kadioglumf.interview.entity.UserEntity;
import com.kadioglumf.interview.mapper.BookMapper;
import com.kadioglumf.interview.mapper.UserMapper;
import com.kadioglumf.interview.model.dto.BookDto;
import com.kadioglumf.interview.model.dto.UserDto;
import com.kadioglumf.interview.repository.UserRepository;
import com.kadioglumf.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Override
    public UserDto findUserById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (!entity.isPresent()) {
            return null;
            //TODO exception
        }
        UserEntity user = entity.get();
        Set<BookDto> bookDtos = bookMapper.bookEntityToListBookDto(user.getBooks());
        UserDto userDto = userMapper.toUserDto(user);
        userDto.setBooks(bookDtos);
        return userDto;
        /*
        return UserDto.builder()
                .id(user.getId())
                //TODO.books(user.getBooks())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

         */

    }

    @Override
    public void createUser(UserDto userDto) {
        UserEntity entity = userMapper.toUserEntity(userDto);
        userRepository.save(entity);
    }

    @Override
    @Transactional
    public UserDto updateUserBook(UserDto userDto, Long bookId) {
        Optional<UserEntity> entity = userRepository.findById(userDto.getId());
        if (!entity.isPresent()) {
            return null;
            //TODO exception
        }
        Set<BookEntity> bookEntity = entity.get().getBooks().stream().filter(c -> !bookId.equals(c.getId())).collect(Collectors.toSet());
        entity.get().setBooks(bookEntity);
        userRepository.save(entity.get());
        return null;
    }

    @Override
    @Transactional
    public UserDto createUserBook(UserDto userDto) {
        Optional<UserEntity> entity = userRepository.findById(userDto.getId());
        if (!entity.isPresent()) {
            return null;
            //TODO exception
        }
        UserEntity userEntity = entity.get();
        userEntity.getBooks().addAll(bookMapper.bookDtoToListBookEntity(userDto.getBooks()));
        userRepository.save(userEntity);
        userEntity = userRepository.findById(userEntity.getId()).get();
        Set<BookDto> bookDtos = bookMapper.bookEntityToListBookDto(userEntity.getBooks());
        UserDto responseUserDto = userMapper.toUserDto(userEntity);
        responseUserDto.setBooks(bookDtos);
        return responseUserDto;
    }
}
