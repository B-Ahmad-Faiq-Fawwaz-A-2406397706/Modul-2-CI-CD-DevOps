package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String createOrderPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "createOrder";
    }

    @GetMapping("/history")
    public String historyFormPage() {
        return "historyForm";
    }

    @PostMapping("/history")
    public String historyListPage(@RequestParam String author, Model model) {
        List<Order> orders = orderService.findAllByAuthor(author);
        model.addAttribute("orders", orders);
        model.addAttribute("author", author);
        return "historyList";
    }

    @GetMapping("/pay/{orderId}")
    public String payOrderPage(@PathVariable String orderId, Model model) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "payOrder";
    }

    @PostMapping("/pay/{orderId}")
    public String payOrderResult(@PathVariable String orderId,
                                 @RequestParam String method,
                                 Model model) {
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("method", method);
        return "payResult";
    }
}