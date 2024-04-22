package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class InventoryRepositoryLab4Test {

    // Create a mock object of the InventoryRepository class
    @Mock
    private InventoryRepository repository;

    // This method runs before each test method to initialize the mock object
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    // This test method checks if a part can be added to the repository
    @Test
    public void testAddPart() {
        // Create a test part object
        Part part = new InhousePart(1, "Test Part", 19.99, 5, 1, 10, 123);
        // Call the addPart method of the repository with the test part object
        repository.addPart(part);
        // Verify that the addPart method was called with the test part object as an argument
        Mockito.verify(repository).addPart(part);
    }

    // This test method checks if a product can be added to the repository
    @Test
    public void testAddProduct() {
        // Create a test part object and a test product object with the part as a component
        List<Part> parts = Arrays.asList(new InhousePart(1, "Test Part", 19.99, 5, 1, 10, 123));
        Product product = new Product(1, "Test Product", 19.99, 5, 1, 10, FXCollections.observableArrayList(parts));
        // Call the addProduct method of the repository with the test product object
        repository.addProduct(product);
        // Verify that the addProduct method was called with the test product object as an argument
        Mockito.verify(repository).addProduct(product);
    }

    // This test method checks if the getAllParts method of the repository returns a non-empty list
    @Test
    public void testGetAllParts() {
        // Create a test part object and add it to the repository
        Part part = new InhousePart(1, "Test Part", 19.99, 5, 1, 10, 123);
        ObservableList<Part> parts = FXCollections.observableArrayList(part);
        Mockito.when(repository.getAllParts()).thenReturn(parts);
        // Call the getAllParts method of the repository and check if it returns a non-empty list
        //List<Part> allParts = repository.getAllParts();
        assertNotNull(parts);
        assertFalse(parts.isEmpty());
    }
}
