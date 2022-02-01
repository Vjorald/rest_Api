package Book_Exchange.Management.Borrow;

import Book_Exchange.Borrow.Borrow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowUnitTests {

    @Test
    public void getInfoTest(){
        Borrow borrow = new Borrow("Adventures of Huckleberry Finn", "57243098573287", "Mark Twain", 20);

        assertEquals("Adventures of Huckleberry Finn",borrow.getName());
        assertEquals("57243098573287",borrow.getIsbn());
        assertEquals("Mark Twain", borrow.getAuthor());
        assertEquals(20,borrow.getNumber());
    }


    @Test
    public void updateNumberTest(){
        Borrow borrow = new Borrow("Adventures of Huckleberry Finn", "57243098573287", "Mark Twain", 20);

        borrow.updateNumber(10);

        assertEquals(10,borrow.getNumber());

    }
}
