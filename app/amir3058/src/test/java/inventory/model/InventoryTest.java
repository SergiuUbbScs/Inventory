package inventory.model;

import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Part part;
    private InventoryRepository repo = new InventoryRepository();;

    @Order(1)
    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("RepeatingTest")
    @Tag("TC1_ECP")
    void TC1_ECP() {
        part = new InhousePart(1, "part1", 20, 3, 1,10, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize + 1;
    }

    @Test
    @Order(2)
    @Tag("TC3_ECP")
    void TC3_ECP() {
        part = new InhousePart(3, "", 2020, 5, 3,9, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("A name has not been entered. ");
    }

    @Test
    @Order(3)
    @Tag("TC4_ECP")
    //@Disabled
    void TC6_ECP() {
        part = new InhousePart(5, "part5", 205, 15, 3,10, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("Inventory level is higher than the maximum value. ");
    }

    @Order(4)
    @Tag("TC8_ECP")
    @ParameterizedTest
    @ValueSource(strings = { "part7" })
    void TC8_ECP(String partName) {
        part = new InhousePart(7, partName, 12345, 0, 3,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("Inventory level must be greater than 0. Inventory level is lower than minimum value. ");
    }

    @Order(5)
    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("RepeatingTest")
    @Tag("TC3_BVA")
    void TC3_BVA() {
        part = new InhousePart(1, "P", 2010, 3, 2,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize + 1;
    }

    @Test
    @Order(6)
    @Tag("TC22_BVA")
    void TC22_BVA() {
        part = new InhousePart(1, "part", 123, 1, 1,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize + 1;
    }

    @Test
    @Order(7)
    @Tag("TC1_BVA")
    void TC1_BVA() {
        part = new InhousePart(1, "", 123, 3, 2,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("A name has not been entered. ");
    }

    @Order(8)
    @Tag("TC21_BVA")
    @ParameterizedTest
    @ValueSource(strings = { "part" })
    void TC21_BVA(String partName) {
        part = new InhousePart(1, partName, 123, -1, 3,5, 1);
        int initialSize = repo.getAllParts().size();
        repo.addPart(part);
        assert repo.getAllParts().size() == initialSize;

        assert InventoryRepository.isValidPart(part).equals("Inventory level must be greater than 0. Inventory level is lower than minimum value. ");
    }
}