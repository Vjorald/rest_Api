package Book_Exchange.Management.Books;

import Book_Exchange.Books.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookUnitTests {

    @Test
    public void testgetInfo() {

        Book book = new Book("Adventures of Huckleberry Finn", "57243098573287", "Mark Twain", 100);

        assertEquals("Adventures of Huckleberry Finn", book.getName());
        assertEquals("57243098573287", book.getIsbn());
        assertEquals("Mark Twain", book.getAuthor());
        assertEquals(100, book.getNumber());

    }


    @Test
    public void testUpdateNumber(){
        Book book = new Book("Adventures of Huckleberry Finn", "57243098573287", "Mark Twain", 100);

        book.updateNumber(20);

        assertEquals(80, book.getNumber());
    }



    @Test
    public void testSetNumber(){
        Book book = new Book("Adventures of Huckleberry Finn", "57243098573287", "Mark Twain", 100);

        book.setNumber(40);

        assertEquals(40, book.getNumber());
    }

}
