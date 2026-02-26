package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepositoryImpl();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    // Update
    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("6f9e96f1-6734-406e-8260-21af6af63bc2");
        product.setProductName("Logitech MX Master 3S");
        product.setProductQuantity(15);
        productRepository.create(product);

        product.setProductName("Logitech MX Master 3S - Graphite");
        product.setProductQuantity(12);
        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product savedProduct = products.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals("Logitech MX Master 3S - Graphite", savedProduct.getProductName());
        assertEquals(12, savedProduct.getProductQuantity());
    }

    @Test
    void testUpdateIfEmpty() {
        Product product = new Product();
        product.setProductId("non-existent-id");
        product.setProductName("Ghost Product");
        product.setProductQuantity(0);

        productRepository.update(product);

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testUpdateIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductName("Keychron K2");
        product1.setProductQuantity(10);
        productRepository.create(product1);
        product1.setProductId("6b79db01-1433-4838-87ac-8cd3f704508d");

        Product product2 = new Product();
        product2.setProductName("DA Meca Sport");
        product2.setProductQuantity(25);
        productRepository.create(product2);
        product2.setProductId("6b79db01-1433-4838-87ac-8cd3f704508e");

        product1.setProductName("Keychron K2 V2");
        product1.setProductQuantity(8);
        productRepository.update(product1);

        Iterator<Product> products = productRepository.findAll();

        Product savedProduct1 = products.next();
        assertEquals("6b79db01-1433-4838-87ac-8cd3f704508d", savedProduct1.getProductId());
        assertEquals("Keychron K2 V2", savedProduct1.getProductName());

        Product savedProduct2 = products.next();
        assertEquals("6b79db01-1433-4838-87ac-8cd3f704508e", savedProduct2.getProductId());

        assertFalse(products.hasNext());
    }

    // Delete
    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductName("Air Fryer Philips");
        product.setProductQuantity(5);
        productRepository.create(product);
        product.setProductId("998d2bde-071f-49da-95d1-279bcb2311d6");

        productRepository.deleteById("998d2bde-071f-49da-95d1-279bcb2311d6");

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        productRepository.deleteById("random-id-123");

        Iterator<Product> products = productRepository.findAll();
        assertFalse(products.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        Product p1 = new Product();
        p1.setProductName("Kopi Kenangan");
        p1.setProductQuantity(50);
        productRepository.create(p1);
        p1.setProductId("998d2bde-071f-49da-95d1-279bcb2311d5");

        Product p2 = new Product();
        p2.setProductName("Janji Jiwa");
        p2.setProductQuantity(40);
        productRepository.create(p2);
        p2.setProductId("998d2bde-071f-49da-95d1-279bcb2311d4");

        productRepository.deleteById("998d2bde-071f-49da-95d1-279bcb2311d5");

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());

        Product remainingProduct = products.next();
        assertEquals("998d2bde-071f-49da-95d1-279bcb2311d4", remainingProduct.getProductId());
        assertEquals("Janji Jiwa", remainingProduct.getProductName());

        assertFalse(products.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductName("Nintendo Switch");
        product.setProductQuantity(20);
        productRepository.create(product);
        product.setProductId("123e4567-e89b-12d3-a456-556642440000");

        assertTrue(productRepository.findById("123e4567-e89b-12d3-a456-556642440000").isPresent());
    }

    @Test
    void testUpdateProductNotFound() {
        Product existingProduct = new Product();
        productRepository.create(existingProduct);
        existingProduct.setProductId("123e4567-e89b-12d3-a456-556642440000");

        Product strangerProduct = new Product();
        strangerProduct.setProductId("679e96f1-6734-406e-8260-21af6af63bc2");

        Product result = productRepository.update(strangerProduct);

        assertNull(result);
    }
}