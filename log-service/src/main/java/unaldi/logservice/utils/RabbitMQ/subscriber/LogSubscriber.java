package unaldi.logservice.utils.RabbitMQ.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import unaldi.logservice.model.dto.LogDTO;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Component
@Slf4j
public class LogSubscriber {
    @RabbitListener(queues = "${rabbitmq.logs.queue}")
    public void fetchLogAndSaveToMongoDB(LogDTO logDTO) {
        log.info("Log record read from queue : " + logDTO);
    }
}
