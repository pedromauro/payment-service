package com.example.payment;

import com.example.payment.service.RabbitNotifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitNotifierTests {

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitNotifier notifier;

    @Test
    @DisplayName("Should send correct message to RabbitMQ when payment status changes")
    public void testMessageSentToRabbit() {
        // Act
        notifier.notifyStatusChange(5L, "REJECTED");

        // Assert
        verify(rabbitTemplate).convertAndSend(eq("payments.status"), contains("REJECTED"));
    }
}
