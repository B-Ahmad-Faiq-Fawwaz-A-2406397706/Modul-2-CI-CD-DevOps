package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {id.ac.ui.cs.advprog.eshop.controller.OrderController.class})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudajat");
    }

    @Test
    void testGetCreateOrderPage() throws Exception {
        doReturn(products).when(productService).findAll();

        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createOrder"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testGetHistoryFormPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("historyForm"));
    }

    @Test
    void testPostHistoryFound() throws Exception {
        List<Order> orders = List.of(order);
        doReturn(orders).when(orderService).findAllByAuthor("Safira Sudajat");

        mockMvc.perform(post("/order/history")
                        .param("author", "Safira Sudajat"))
                .andExpect(status().isOk())
                .andExpect(view().name("historyList"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attribute("author", "Safira Sudajat"));
    }

    @Test
    void testPostHistoryNotFound() throws Exception {
        doReturn(new ArrayList<>()).when(orderService).findAllByAuthor("Unknown");

        mockMvc.perform(post("/order/history")
                        .param("author", "Unknown"))
                .andExpect(status().isOk())
                .andExpect(view().name("historyList"))
                .andExpect(model().attribute("orders", new ArrayList<>()));
    }

    @Test
    void testGetPayOrderPage() throws Exception {
        doReturn(order).when(orderService).findById("13652556-012a-4c07-b546-54eb1396d79b");

        mockMvc.perform(get("/order/pay/13652556-012a-4c07-b546-54eb1396d79b"))
                .andExpect(status().isOk())
                .andExpect(view().name("payOrder"))
                .andExpect(model().attributeExists("order"));
    }

    @Test
    void testPostPayOrder() throws Exception {
        doReturn(order).when(orderService).findById("13652556-012a-4c07-b546-54eb1396d79b");

        mockMvc.perform(post("/order/pay/13652556-012a-4c07-b546-54eb1396d79b")
                        .param("method", "BANK_TRANSFER"))
                .andExpect(status().isOk())
                .andExpect(view().name("payResult"))
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attribute("method", "BANK_TRANSFER"));
    }
}