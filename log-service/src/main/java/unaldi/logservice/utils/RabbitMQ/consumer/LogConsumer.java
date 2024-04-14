package unaldi.logservice.utils.RabbitMQ.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unaldi.logservice.repository.LogRepository;
import unaldi.logservice.service.abstracts.mapper.LogMapper;
import unaldi.logservice.utils.RabbitMQ.response.LogResponse;
import unaldi.logservice.utils.constant.Messages;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Component
@Slf4j
public class LogConsumer {
    private final LogRepository logRepository;

    @Autowired
    public LogConsumer(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @RabbitListener(queues = "${rabbitmq.logs.queue}")
    public void fetchLogAndSaveToMongoDB(LogResponse logResponse) {
        log.info(Messages.LOG_READ_QUEUE + " : {}", logResponse);

        this.logRepository.save(LogMapper.INSTANCE.convertToLog(logResponse));
    }
}
