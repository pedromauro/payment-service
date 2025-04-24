package com.example.payment.service;

import com.example.payment.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitNotifier {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitNotifier(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notifyStatusChange(Long paymentId, String newStatus) {
        String message = String.format("Payment ID: %d changed to status: %s", paymentId, newStatus);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, message);
    }
}
