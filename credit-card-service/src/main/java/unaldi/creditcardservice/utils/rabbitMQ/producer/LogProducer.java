package unaldi.creditcardservice.utils.rabbitMQ.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unaldi.creditcardservice.utils.rabbitMQ.request.LogRequest;
import unaldi.creditcardservice.utils.configuration.rabbitMQ.RabbitMQProducerConfiguration;
import unaldi.creditcardservice.utils.constant.Messages;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Component
@Slf4j
public class LogProducer {
    private final RabbitMQProducerConfiguration rabbitMQProducerConfiguration;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public LogProducer(RabbitMQProducerConfiguration rabbitMQProducerConfiguration, AmqpTemplate amqpTemplate) {
        this.rabbitMQProducerConfiguration = rabbitMQProducerConfiguration;
        this.amqpTemplate = amqpTemplate;
    }

    public void sendToLog(LogRequest logRequest) {
        log.info(Messages.LOG_WRITE_QUEUE + " : {}", logRequest);

        amqpTemplate.convertAndSend(
                rabbitMQProducerConfiguration.getExchange(),
                rabbitMQProducerConfiguration.getRoutingKey(),
                logRequest
        );
    }
}
