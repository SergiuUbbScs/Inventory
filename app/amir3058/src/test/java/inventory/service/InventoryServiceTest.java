package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryReadWriteFromFileRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    private String denumire;
    private int pret;
    private int numar_bucati;
    private int numar_minim;
    private int numar_maxim;
    private int id_masina;
    private InventoryReadWriteFromFileRepository inventoryRepository;
    private InventoryService inventoryService;
    private InhousePart inhousePart;
    @BeforeEach
    void setUp() {
        try {
            PrintWriter myObj=new PrintWriter("E:\\sem6\\VVSS\\labs\\Inventory\\app\\amir3058\\src\\main\\java\\data\\items.txt");
            myObj.print("");
            myObj.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventoryRepository=new InventoryReadWriteFromFileRepository();
        inventoryService=new InventoryService(inventoryRepository);
        numar_bucati=2;
        numar_minim=3;
        numar_maxim=4;
        id_masina=6;
    }

    @AfterEach
    void tearDown() {}

    @ParameterizedTest

    @DisplayName("Test ECP 1/Test BVA 5")

    @ValueSource(strings = { "parte", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" })
    @RepeatedTest(3)
    @Tag("ecp")
    void TC1_ECP() throws Exception {
        //arrange
        denumire="parte";
        pret=100;
        InhousePart inhousePart = new InhousePart(inventoryRepository.getAutoPartId(),denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        //act
        Part parte= inventoryRepository.getAllParts().get(0);
        //assert
        Assertions.assertEquals(inhousePart.getName(),parte.getName());
        Assertions.assertEquals(inhousePart.getPrice(),parte.getPrice());
    }

    @Test
    @Tag("ecp")
    @DisplayName("Test ECP 2")
    void TC2_ECP() {
        //arrange
        denumire = "parte";
        pret = -100;
        //act
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            inventoryService.addInhousePart(denumire, pret, numar_bucati, numar_minim, numar_maxim, id_masina);
        });
        //assert
        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test()
    @Tag("ecp")
    @DisplayName("Test ECP 3")
    void TC3_ECP() {
        //arrange
        denumire="parte";
        String pret2="asd";
        //act

        //assert
        Assertions.assertThrows(NumberFormatException.class,()->{inventoryService.addInhousePart(denumire,Integer.parseInt(pret2),numar_bucati,numar_minim,numar_maxim,id_masina);});
    }

    @Test()
    @Tag("ecp")
    @DisplayName("Test ECP 4")
    void TC4_ECP() {
        //arrange
        int denumire2=123;
        pret=300;
        //act

        //assert
        Assertions.assertThrows(NumberFormatException.class,()->{inventoryService.addInhousePart(String.valueOf(denumire2),pret,numar_bucati,numar_minim,numar_maxim,id_masina);});
    }

    @Test
    @Tag("bva")
    @Timeout(5)
    @DisplayName("Test 1 BVA")
    void TC1_BVA() {
        //arrange
        denumire="";
        pret=100;
        //act

        //assert
        Assertions.assertThrows(Exception.class,()->{inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);});
    }

    @Test
    @Tag("bva")
    @Timeout(5)
    @DisplayName("Test 7 BVA")
    void TC7_BVA() {
        //arrange
        denumire="M";
        pret=0;
        //act

        //assert
        Assertions.assertThrows(Exception.class,()->{inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);});
    }

    @Test
    @Tag("bva")
    @Timeout(5)
    @DisplayName("Test 9 BVA")
    void TC9_BVA() throws Exception {
        //arrange
        denumire="M";
        pret=2;
        InhousePart inhousePart = new InhousePart(inventoryRepository.getAutoPartId(),denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        inventoryService.addInhousePart(denumire,pret,numar_bucati,numar_minim,numar_maxim,id_masina);
        //act
        List<Part> allParts = inventoryRepository.getAllParts();
        int lastIndex = allParts.size() - 1;
        Part lastPart = allParts.get(lastIndex);
        //assert
        Assertions.assertEquals(inhousePart.getName(),lastPart.getName());
    }
}