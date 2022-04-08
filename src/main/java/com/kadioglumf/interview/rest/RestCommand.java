package com.kadioglumf.interview.rest;

import com.kadioglumf.interview.enums.OutgoingOperationNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RestCommand<Req, Res> {

    private Req request;
    private String url;
    private ResponseEntity<Res> responseEntity;
    private Class<Res> responseClass;
    private Date createDate;
    private Date responseDate;
    private Exception exception;
    private OutgoingOperationNameEnum outgoingOperationNameEnum;
    private boolean isFinished = false;
    private int timeOut;

    private RestCommand(final String url, int timeOut, final Req request, final Class<Res> responseClass) {
        this.url = url;
        this.timeOut = timeOut;
        this.request = request;
        this.createDate = new Date();
        this.responseClass = responseClass;
    }

    public static <Req, Res> RestCommand<Req, Res> get(final String url, int timeOut, final Req request, Class<Res> responseClass) {
        return new RestCommand<>(url, timeOut, request, responseClass);
    }

    public void setResult(final ResponseEntity<Res> responseEntity) {
        this.responseEntity = responseEntity;
        this.responseDate = new Date();
        this.isFinished = true;
    }

    public void setResult(final Exception e) {
        this.exception = e;
        this.responseDate = new Date();
        this.isFinished = true;
    }
}
