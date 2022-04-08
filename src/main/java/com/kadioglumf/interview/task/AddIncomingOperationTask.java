package com.kadioglumf.interview.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadioglumf.interview.entity.IncomingOperation;
import com.kadioglumf.interview.repository.IncomingOperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

@Slf4j
public class AddIncomingOperationTask implements Callable<Boolean> {

    private static final Logger INCOMING_LOGGER = LogManager.getLogger("Incoming");

    private final IncomingOperationRepository repository;

    private final IncomingOperation entity;

    public AddIncomingOperationTask(IncomingOperationRepository repository, IncomingOperation entity) {
        this.repository = repository;
        this.entity = entity;
    }

    @Override
    public Boolean call() {
        try {
            repository.save(entity);

            INCOMING_LOGGER.info(new ObjectMapper().writeValueAsString(entity));
            if (log.isDebugEnabled()) {
                log.debug("Inserted {} incoming operation entry for operationId {} successfully.", entity.getUrl() != null ? entity.getUrl() : "", entity.getOperationId());
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Failed to insert {} incoming operation entry for operationId {}.\n", (entity != null && entity.getUrl() != null) ? entity.getUrl() : "", entity.getOperationId());
                log.error(e.getMessage(), e);
            }
        }
       return Boolean.TRUE;
    }
}
