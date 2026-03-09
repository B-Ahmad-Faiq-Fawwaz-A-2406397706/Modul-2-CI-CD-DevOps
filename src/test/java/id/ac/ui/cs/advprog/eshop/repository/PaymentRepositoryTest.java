package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "BCA");
        bankTransferData.put("referenceCode", "REF001");
        payment1 = new Payment("pay-001", "BANK_TRANSFER", bankTransferData);

        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");
        payment2 = new Payment("pay-002", "VOUCHER", voucherData);
    }

    @Test
    void testSaveCreate() {
        Payment result = paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        paymentRepository.save(payment1);
        payment1.setStatus("REJECTED");
        paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals("REJECTED", findResult.getStatus());
    }

    @Test
    void testFindByIdFound() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Payment result = paymentRepository.findById("pay-001");
        assertEquals("pay-001", result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        paymentRepository.save(payment1);

        Payment result = paymentRepository.findById("zzz");
        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(2, result.size());
    }
}
