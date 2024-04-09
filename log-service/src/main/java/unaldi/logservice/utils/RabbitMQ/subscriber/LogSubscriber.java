package unaldi.logservice.utils.RabbitMQ.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.repository.LogRepository;
import unaldi.logservice.service.abstracts.mapper.LogMapper;
import unaldi.logservice.utils.constant.Messages;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Component
@Slf4j
public class LogSubscriber {
    private final LogRepository logRepository;

    @Autowired
    public LogSubscriber(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @RabbitListener(queues = "${rabbitmq.logs.queue}")
    public void fetchLogAndSaveToMongoDB(LogDTO logDTO) {
        log.info(Messages.LOG_READ_QUEUE + " : " + logDTO);

        this.logRepository.save(LogMapper.INSTANCE.convertToLog(logDTO));
    }
}
