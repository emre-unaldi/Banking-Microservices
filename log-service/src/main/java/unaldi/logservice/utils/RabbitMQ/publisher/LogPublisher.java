package unaldi.logservice.utils.RabbitMQ.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unaldi.logservice.model.Log;
import unaldi.logservice.model.dto.LogDTO;
import unaldi.logservice.utils.configuration.RabbitMQPublisherConfiguration;
import unaldi.logservice.utils.constant.Messages;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Component
@Slf4j
public class LogPublisher {
    private final RabbitMQPublisherConfiguration rabbitMQPublisherConfiguration;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public LogPublisher(RabbitMQPublisherConfiguration rabbitMQPublisherConfiguration, AmqpTemplate amqpTemplate) {
        this.rabbitMQPublisherConfiguration = rabbitMQPublisherConfiguration;
        this.amqpTemplate = amqpTemplate;
    }

    public void sendLog(LogDTO logDTO) {
        log.info(Messages.LOG_WRITE_QUEUE + " : " + logDTO);

        amqpTemplate.convertSendAndReceive(
                rabbitMQPublisherConfiguration.getExchange(),
                rabbitMQPublisherConfiguration.getRoutingKey(),
                logDTO
        );
    }
}
