package com.example.payment;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.service.PaymentService;
import com.example.payment.service.RabbitNotifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RabbitNotifier notifier;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void testChangeStatusTriggersNotification() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setStatus("PENDING");

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment updated = paymentService.changeStatus(1L, "COMPLETED");

        assertEquals("COMPLETED", updated.getStatus());
        verify(notifier).notifyStatusChange(1L, "COMPLETED");
    }
}
