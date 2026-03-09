package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/detail")
    public String detailFormPage() {
        return "detail";
    }

    @GetMapping("/detail/{paymentId}")
    public String detailPage(@PathVariable String paymentId, Model model) {
        try {
            Payment payment = paymentService.getPayment(paymentId);
            model.addAttribute("payment", payment);
            return "detailResult";
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        }
    }

    @GetMapping("/admin/list")
    public String adminListPage(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "adminList";
    }

    @GetMapping("/admin/detail/{paymentId}")
    public String adminDetailPage(@PathVariable String paymentId, Model model) {
        Payment payment = paymentService.getPayment(paymentId);
        model.addAttribute("payment", payment);
        return "adminDetail";
    }

    @PostMapping("/admin/set-status/{paymentId}")
    public String setStatus(@PathVariable String paymentId,
                            @RequestParam String status,
                            Model model) {
        Payment payment = paymentService.getPayment(paymentId);
        paymentService.setStatus(payment, null, status);
        model.addAttribute("payment", payment);
        return "adminDetail";
    }
}
