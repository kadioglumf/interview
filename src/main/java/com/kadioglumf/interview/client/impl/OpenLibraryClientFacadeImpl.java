package com.kadioglumf.interview.client.impl;

import com.google.gson.Gson;
import com.kadioglumf.interview.enums.OutgoingOperationNameEnum;
import com.kadioglumf.interview.rest.BaseRestClient;
import com.kadioglumf.interview.rest.RestCommand;
import com.kadioglumf.interview.client.OpenLibraryClientFacade;
import com.kadioglumf.interview.model.response.BookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OpenLibraryClientFacadeImpl extends BaseRestClient implements OpenLibraryClientFacade {

    @Value("${openLibrary.services.url}")
    private String servicesUrl;

    private final Gson gson = new Gson();

    @Override
    public BookResponse findBooksWithByText(String searchKey) {
        BookResponse response = new BookResponse();
        RestCommand<Object, BookResponse> restCommand;
        Date requestTime = null;
        Date responseTime = null;
        String requestData ="";
        String responseData = "";
        String url = "";
       try {
           url = servicesUrl + searchKey;
           restCommand = RestCommand.get(url, 10000,null, BookResponse.class);
           requestTime = new Date();
           restCommand = get(restCommand);
           responseTime = new Date();
           response = restCommand.getResponseEntity().getBody();
           responseData = gson.toJson(response);
       } catch (Exception ex) {

       } finally {
           addRsLog(requestTime, requestData, responseTime, responseData,
                   OutgoingOperationNameEnum.OPEN_LIBRARY_CLIENT_FIND_BOOKS_WITH_BY_TEXT.name(), url);
       }
       return response;
    }
}
