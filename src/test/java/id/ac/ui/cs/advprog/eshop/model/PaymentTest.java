package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Map<String, String> voucherData;
    private Map<String, String> bankTransferData;

    @BeforeEach
    void setUp() {
        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "BCA");
        bankTransferData.put("referenceCode", "REF123456");
    }

    @Test
    void testCreatePaymentVoucherValidCode() {
        Payment payment = new Payment("pay-001", "VOUCHER", voucherData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherInvalidLength() {
        voucherData.put("voucherCode", "ESHOP123ABC");
        Payment payment = new Payment("pay-002", "VOUCHER", voucherData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherNotStartWithESHOP() {
        voucherData.put("voucherCode", "TOKO12345ABC6789");
        Payment payment = new Payment("pay-003", "VOUCHER", voucherData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherInsufficientNumericalChars() {
        voucherData.put("voucherCode", "ESHOPABCDEFGHIJK");
        Payment payment = new Payment("pay-004", "VOUCHER", voucherData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherNullCode() {
        voucherData.put("voucherCode", null);
        Payment payment = new Payment("pay-005", "VOUCHER", voucherData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferValid() {
        Payment payment = new Payment("pay-006", "BANK_TRANSFER", bankTransferData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferEmptyBankName() {
        bankTransferData.put("bankName", "");
        Payment payment = new Payment("pay-007", "BANK_TRANSFER", bankTransferData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferNullBankName() {
        bankTransferData.put("bankName", null);
        Payment payment = new Payment("pay-008", "BANK_TRANSFER", bankTransferData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferEmptyReferenceCode() {
        bankTransferData.put("referenceCode", "");
        Payment payment = new Payment("pay-009", "BANK_TRANSFER", bankTransferData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferNullReferenceCode() {
        bankTransferData.put("referenceCode", null);
        Payment payment = new Payment("pay-010", "BANK_TRANSFER", bankTransferData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusValid() {
        Payment payment = new Payment("pay-011", "BANK_TRANSFER", bankTransferData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = new Payment("pay-012", "BANK_TRANSFER", bankTransferData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
}