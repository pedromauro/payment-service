package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitNotifier notifier;

    @Transactional
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional(readOnly = true)
    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + id + " not found"));
    }

    @Transactional
    public Payment changeStatus(Long id, String newStatus) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with ID " + id + " not found"));

        payment.setStatus(newStatus);
        Payment updatedPayment = paymentRepository.save(payment);

        // Nota: Esta operación no participa en la transacción de la DB
        notifier.notifyStatusChange(id, newStatus);

        return updatedPayment;
    }
}
