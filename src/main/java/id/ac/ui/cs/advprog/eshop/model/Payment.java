package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {

    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = resolveStatus(method, paymentData);
    }

    private String resolveStatus(String method, Map<String, String> paymentData) {
        if ("VOUCHER".equals(method)) {
            return validateVoucher(paymentData);
        } else if ("BANK_TRANSFER".equals(method)) {
            return validateBankTransfer(paymentData);
        }
        return "REJECTED";
    }

    private String validateVoucher(Map<String, String> paymentData) {
        String code = paymentData.get("voucherCode");
        if (code == null) return "REJECTED";
        if (code.length() != 16) return "REJECTED";
        if (!code.startsWith("ESHOP")) return "REJECTED";

        long numCount = code.chars().filter(Character::isDigit).count();
        if (numCount != 8) return "REJECTED";

        return "SUCCESS";
    }

    private String validateBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty()) return "REJECTED";
        if (referenceCode == null || referenceCode.isEmpty()) return "REJECTED";

        return "SUCCESS";
    }

    public void setStatus(String status) {
        if (!status.equals("SUCCESS") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = status;
    }
}