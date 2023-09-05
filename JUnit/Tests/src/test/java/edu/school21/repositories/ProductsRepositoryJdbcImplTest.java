package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase db;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<Product>(){{
        add(new Product(1L, "Gunpowder", 100.99));
        add(new Product(2L, "Airplain", 1500.0));
        add(new Product(3L, "Bottle", 10.0));
        add(new Product(4L, "Salt", 22.99));
        add(new Product(5L, "LayS", 120.0));
    }};
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(4L, "Salt", 22.99);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Coca-cola", 1000.0);
    final List<Product> EXPECTED_SAVE_PRODUCT = new ArrayList<Product>(){{
        add(new Product(1L, "Gunpowder", 100.99));
        add(new Product(2L, "Airplain", 1500.0));
        add(new Product(3L, "Bottle", 10.0));
        add(new Product(4L, "Salt", 22.99));
        add(new Product(5L, "LayS", 120.0));
        add(new Product(6L, "pig snout", 1000.0));
    }};
    final Product FOR_SAVE_PRODUCT = new Product(6L, "pig snout", 1000.0);
    final Product ERROR_UPDATE_PRODUCT = new Product(100L, "aboba", 000.0);
    final Product ERROR_SAVE_PRODUCT = new Product(5L, "aboba", 000.0);
    final List<Product> EXPECTED_DELETE_PRODUCTS = new ArrayList<Product>(){{
        add(new Product(1L, "Gunpowder", 100.99));
        add(new Product(2L, "Airplain", 1500.0));
        add(new Product(4L, "Salt", 22.99));
        add(new Product(5L, "LayS", 120.0));
    }};

    @Test
    public void testFindAll() {
        ProductsRepository pr = new ProductsRepositoryJdbcImpl();
        Assertions.assertEquals(pr.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    public void testFindById() {
        ProductsRepository pr = new ProductsRepositoryJdbcImpl();
        Assertions.assertEquals(pr.findById(4L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
        Assertions.assertEquals(pr.findById(100L), Optional.empty());
    }

    @Test
    public void testUpdate() {
        ProductsRepository pr = new ProductsRepositoryJdbcImpl();
        pr.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertTrue(pr.findById(3L).isPresent());
        Assertions.assertEquals(pr.findById(3L).get(), EXPECTED_UPDATED_PRODUCT);
        Assertions.assertThrows(RuntimeException.class ,() -> pr.update(ERROR_UPDATE_PRODUCT));
    }

    @Test
    public void testInsert() {
        ProductsRepository pr = new ProductsRepositoryJdbcImpl();
        pr.save(FOR_SAVE_PRODUCT);
        Assertions.assertEquals(pr.findAll(), EXPECTED_SAVE_PRODUCT);
        Assertions.assertThrows(RuntimeException.class ,() -> pr.save(ERROR_SAVE_PRODUCT));
    }

    @Test
    public void testDelete() {
        ProductsRepository pr = new ProductsRepositoryJdbcImpl();
        pr.delete(3L);
        Assertions.assertEquals(pr.findAll(), EXPECTED_DELETE_PRODUCTS);
        Assertions.assertThrows(RuntimeException.class ,() -> pr.delete(100L));
    }
}
