package com.kadioglumf.interview.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class TaskRunnerService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public <T> void run(final Callable<T> callable) {
        try {
            executorService.submit(callable);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("Failed to run asynchronous task", e);
            }
        }
    }

}
