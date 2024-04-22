package inventory.model;

import javafx.collections.ObservableList;
//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryLab4Test {
    public Inventory inventory;

    @BeforeEach
    @Test
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void testGetAllParts() {
        ObservableList<Part> parts = inventory.getAllParts();
        assertNotNull(parts);
        assertEquals(0, parts.size());
    }

    @Test
    public void testGetAllProducts() {
        ObservableList<Product> products = inventory.getProducts();
        assertNotNull(products);
        assertEquals(0, products.size());
    }

    @Test
    public void testAddProduct() {
        int initialSize = inventory.getProducts().size();
        Product product = new Product(1, "Test Product", 19.99, 5, 1, 10, null);
        inventory.addProduct(product);
        assertEquals(initialSize + 1, inventory.getProducts().size());
        assertNotNull(inventory.lookupProduct("Test Product"));
    }

    @Test
    public void testAddPart() {
        int initialSize = inventory.getAllParts().size();
        InhousePart part = new InhousePart(1, "Test Part", 19.99, 5, 1, 10, 1);
        inventory.addPart(part);
        assertEquals(initialSize + 1, inventory.getAllParts().size());
        assertNotNull(inventory.lookupPart("Test Part"));
    }
}
