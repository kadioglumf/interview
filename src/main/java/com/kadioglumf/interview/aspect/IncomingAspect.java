package com.kadioglumf.interview.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kadioglumf.interview.annotations.IncomingLogger;
import com.kadioglumf.interview.entity.IncomingOperation;
import com.kadioglumf.interview.enums.IncomingOperationNameEnum;
import com.kadioglumf.interview.repository.IncomingOperationRepository;
import com.kadioglumf.interview.task.AddIncomingOperationTask;
import com.kadioglumf.interview.task.TaskRunnerService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class IncomingAspect {

    @Autowired
    IncomingOperationRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TaskRunnerService taskRunnerService;

    private final Gson gson = new Gson();

    @Around(value = "@annotation(incomingLogger)")
    private Object logging(ProceedingJoinPoint joinPoint, IncomingLogger incomingLogger) throws Throwable {
        Object retVal;
        IncomingOperation incomingOperation = new IncomingOperation();
        IncomingOperationNameEnum operationName = incomingLogger.operationName();
        try {
            incomingOperation.setRequestTime(new Date());
            incomingOperation.setTransactionId(UUID.randomUUID().toString());
            incomingOperation.setUrl(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()");
            incomingOperation.setOperationName(operationName.name());
            StringBuilder apiLog = new StringBuilder();
            apiLog.append(gson.toJson(joinPoint.getArgs()));

            log.debug(apiLog.toString());
            incomingOperation.setRequestData(apiLog.toString());
            retVal = joinPoint.proceed();
            incomingOperation.setResponseTime(new Date());
            incomingOperation.setResponseData(objectMapper.writeValueAsString(retVal));
            incomingOperation.setStatusCode(HttpServletResponse.SC_OK);
            log.debug("Response: {}", objectMapper.writeValueAsString(retVal));
        } catch (Exception ex) {
            log.debug("Exception : {}", ex.getMessage());
            incomingOperation.setResponseTime(new Date());
            incomingOperation.setResponseData(ex.toString());
            incomingOperation.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw ex;
        } finally {
            taskRunnerService.run(new AddIncomingOperationTask(repository, incomingOperation));
        }
        log.debug("incomingOperation: {}", incomingOperation);
        return retVal;
    }
}
