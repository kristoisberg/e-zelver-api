package ee.cs.ut.esi.ezelver.service;

import ee.cs.ut.esi.ezelver.model.Payment;
import ee.cs.ut.esi.ezelver.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FinancialService {
    private final PaymentRepository paymentRepository;

    public Payment fetchPaymentById(int paymentId) {
        return paymentRepository.getById(paymentId);
    }

    public Payment createPayment(float amount) {
        Payment payment = new Payment(new Date(), amount);
        return paymentRepository.save(payment);
    }
}
