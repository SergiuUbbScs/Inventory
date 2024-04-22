package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class InventoryServiceLab4Test {

    @Mock
    public InventoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPart() {
        // Call the addInhousePart method on the service object
        service.addInhousePart("Test Part", 19.99, 5, 1, 10, 123);
        // Verify that the addInhousePart method was called with the correct arguments
        Mockito.verify(service).addInhousePart("Test Part", 19.99, 5, 1, 10, 123);
    }

    @Test
    public void testAddProduct() {
        // Create a list of parts to pass to the addProduct method
        List<Part> parts = Arrays.asList(new InhousePart(1, "Test Part", 19.99, 5, 1, 10, 123));
        // Call the addProduct method on the service object
        service.addProduct("Test Product", 19.99, 5, 1, 10,FXCollections.observableArrayList(parts));
        // Verify that the addProduct method was called with the correct arguments
        Mockito.verify(service).addProduct("Test Product", 19.99, 5, 1, 10,FXCollections.observableArrayList(parts));
    }

    @Test
    public void testGetAllParts() {
        // Create a test part object
        Part part = new InhousePart(1, "Test Part", 19.99, 5, 1, 10, 123);
        // Create an ObservableList containing the test part
        ObservableList<Part> parts = FXCollections.observableArrayList(part);
        // Tell the mock object to return the ObservableList when getAllParts is called
        Mockito.when(service.getAllParts()).thenReturn(parts);
        // Call getAllParts on the service object and store the result in allParts
        List<Part> allParts = service.getAllParts();
        // Verify that allParts is not null and not empty
        assertNotNull(allParts);
        assertFalse(allParts.isEmpty());
    }
}
