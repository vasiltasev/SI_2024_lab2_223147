import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void EveryBranch(){
        Item item1 = new Item("Shampoo","-123",350,0.1f);
        List<Item> items = new ArrayList<>();
        items.add(item1);
        Item item2 = new Item("Shampoo",null,350,0.1f);
        List<Item> items2 = new ArrayList<>();
        items2.add(item2);

        RuntimeException ex ;

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null,1000));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items,1000));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items2,1000));
        assertTrue(ex.getMessage().contains("No barcode!"));

        Item tc8 = new Item("Shampoo", "0123456789", 400, 0.1f);
        List<Item> items8 = new ArrayList<>();
        items8.add(tc8);
        assertTrue(SILab2.checkCart(items8, 1000));
    }

    @Test
    void MultipleConditionTest(){
        //(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

        //T && T && T
        Item item1 = new Item("Shampoo","0123",350,0.1f);

        assertTrue(item1.getPrice() > 300);
        assertTrue(item1.getDiscount() > 0);
        assertEquals('0', item1.getBarcode().charAt(0));

        //T && T && F
        Item item2 = new Item("Shampoo","2223",350,0.1f);

        assertTrue(item2.getPrice() > 300);
        assertTrue(item2.getDiscount() > 0);
        assertNotEquals('0', item2.getBarcode().charAt(0));

        //T && F...
        Item item3 = new Item("Shampoo","2223",350,0);

        assertTrue(item3.getPrice() > 300);
        assertFalse(item3.getDiscount() > 0);

        //F ...
        Item item4 = new Item("Shampoo","2223",250,0);

        assertFalse(item4.getPrice() > 300);
    }
}