package inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryRepositoryTest {

    InventoryRepository repo;
    InventoryRepository emptyListRepo;

    Product prod;
    Product prod2;
    Product prod3;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();

        prod = new Product(1, "nume_gasit", 1.0, 0, 0, 0, null);
        prod2 = new Product(2, "produs_second", 2.0, 0, 0, 0, null);
        prod3 = new Product(3, "prod", 3.0, 0, 0, 0, null);

        repo.addProduct(prod);
        repo.addProduct(prod2);
        repo.addProduct(prod3);

        emptyListRepo = new InventoryRepository();
    }

    @Test
    void lookupProductOkByName() {
        Product productFound = repo.lookupProduct("nume_gasit");

        assertEquals(prod, productFound);
    }

    @Test
    void lookupProductOkById() {
        Product productFound = repo.lookupProduct("2");

        assertEquals(prod2, productFound);
    }

    @Test
    void lookupProductNoSearchResults() {
        Product productFound = repo.lookupProduct("nu_se_gaseste");

        assertNull(productFound);
    }

    @Test
    void lookupProductEmptyList() {
        Product emptyProduct = new Product(0, null, 0.0, 0, 0, 0, null);

        Product productFound = emptyListRepo.lookupProduct("lista_nepopulata");

        assertEquals(emptyProduct, productFound);
    }

    @Test
    void lookupProduct4thElem() {
        Product productFound = repo.lookupProduct("produs_second");

        assertEquals(prod2, productFound);
    }
}
