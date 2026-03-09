package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {PaymentController.class})
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF001");

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        payment = new Payment("pay-001", "BANK_TRANSFER", paymentData);
    }

    @Test
    void testGetDetailFormPage() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("detail"));
    }

    @Test
    void testGetDetailPageFound() throws Exception {
        doReturn(payment).when(paymentService).getPayment("pay-001");

        mockMvc.perform(get("/payment/detail/pay-001"))
                .andExpect(status().isOk())
                .andExpect(view().name("detailResult"))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void testGetDetailPageNotFound() throws Exception {
        doThrow(new NoSuchElementException()).when(paymentService).getPayment("zzz");

        mockMvc.perform(get("/payment/detail/zzz"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAdminListPage() throws Exception {
        doReturn(List.of(payment)).when(paymentService).getAllPayments();

        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminList"))
                .andExpect(model().attributeExists("payments"));
    }

    @Test
    void testGetAdminDetailPage() throws Exception {
        doReturn(payment).when(paymentService).getPayment("pay-001");

        mockMvc.perform(get("/payment/admin/detail/pay-001"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminDetail"))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void testPostSetStatus() throws Exception {
        doReturn(payment).when(paymentService).getPayment("pay-001");
        doReturn(payment).when(paymentService).setStatus(payment, null, "SUCCESS");

        mockMvc.perform(post("/payment/admin/set-status/pay-001")
                        .param("status", "SUCCESS"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminDetail"))
                .andExpect(model().attributeExists("payment"));
    }
}