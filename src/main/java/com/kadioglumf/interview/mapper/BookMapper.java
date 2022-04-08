package com.kadioglumf.interview.mapper;

import com.kadioglumf.interview.entity.BookEntity;
import com.kadioglumf.interview.model.dto.BookDto;
import com.kadioglumf.interview.model.request.BookRequestModel;
import com.kadioglumf.interview.model.response.DocResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookRequestModel toBookRequestModel(BookDto bookDto);
    BookDto toBookDto(BookRequestModel requestModel);
    List<BookDto> toListBookDto(List<DocResponse> response);

    @Mapping(source = "entity", target = "firstSentence", qualifiedByName = "toListFirstSentence")
    @Mapping(source = "entity", target = "authorKey", qualifiedByName = "toListAuthorKey")
    @Mapping(source = "entity", target = "authorName", qualifiedByName = "toListAuthorName")
    BookDto toBookDto(BookEntity entity);
    Set<BookDto> bookEntityToListBookDto(Set<BookEntity> entities);

    @Named("toListFirstSentence")
    default List<String> toListFirstSentence(BookEntity entity) {
        return Arrays.asList(entity.getFirstSentence().split(","));
    }

    @Named("toListAuthorKey")
    default List<String> toListAuthorKey(BookEntity entity) {
        return Arrays.asList(entity.getAuthorKey().split(","));
    }

    @Named("toListAuthorName")
    default List<String> toListAuthorName(BookEntity entity) {
        return Arrays.asList(entity.getAuthorName().split(","));
    }



    @Mapping(source = "bookDto", target = "firstSentence", qualifiedByName = "toStringFirstSentence")
    @Mapping(source = "bookDto", target = "authorKey", qualifiedByName = "stringToListAuthorKey")
    @Mapping(source = "bookDto", target = "authorName", qualifiedByName = "stringToListAuthorName")
    BookEntity toBookEntity(BookDto bookDto);
    Set<BookEntity> bookDtoToListBookEntity(Set<BookDto> bookDtos);

    @Named("toStringFirstSentence")
    default String toStringFirstSentence(BookDto bookDto) {
        return String.join(",", bookDto.getFirstSentence());
    }

    @Named("stringToListAuthorKey")
    default String stringToListAuthorKey(BookDto bookDto) {
        return String.join(",", bookDto.getAuthorKey());
    }

    @Named("stringToListAuthorName")
    default String stringToListAuthorName(BookDto bookDto) {
        return String.join(",", bookDto.getAuthorName());
    }

}
