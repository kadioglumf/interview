package com.kadioglumf.interview.rest;

import com.kadioglumf.interview.entity.OutgoingOperation;
import com.kadioglumf.interview.repository.OutgoingOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BaseRestClient<Request, Response> {

    @Autowired
    protected OutgoingOperationRepository outgoingOperationRepository;

    public RestCommand get(final RestCommand restCommand) throws Exception {
        doGet(restCommand);
        return restCommand;
    }

    private void doGet(final RestCommand<Request, Response> restCommand) throws Exception {
        final RestTemplate restTemplate = RestTemplateFactory.create(restCommand.getTimeOut(), restCommand.getTimeOut());
        if (restCommand.getResponseClass().getSimpleName().equals(String.class.getSimpleName())) {
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        }
        try {
            final ResponseEntity<Response> responseEntity;
            if(List.class.isAssignableFrom(restCommand.getResponseClass())) {
                responseEntity = restTemplate.exchange(restCommand.getUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<Response>() {});
            } else  {
                responseEntity = restTemplate.exchange(restCommand.getUrl(), HttpMethod.GET, null, restCommand.getResponseClass());
            }
            restCommand.setResult(responseEntity);
            if (restCommand.getResponseEntity() == null || restCommand.getResponseEntity().getBody() == null) {
                throw new Exception("Response body is null");
            }
        }  catch (final ResourceAccessException e) {
            restCommand.setResult(e);
            //TODO
        } catch (final Exception e) {
            restCommand.setResult(e);
            throw new Exception("ccc");
        }
    }

    public void addRsLog(Date requestTime, String requestData, Date responseTime, String responseData,
                         String operationName, String url) {

        OutgoingOperation ogEntity = new OutgoingOperation();
        ogEntity.setTransactionId(UUID.randomUUID().toString());
        ogEntity.setOperationName(operationName);
        ogEntity.setRequestTime(requestTime);
        ogEntity.setRequestData(requestData);
        ogEntity.setResponseTime(responseTime);
        ogEntity.setResponseData(responseData);
        ogEntity.setUrl(url);
        outgoingOperationRepository.save(ogEntity);
    }
}
