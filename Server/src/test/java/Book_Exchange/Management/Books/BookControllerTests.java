package Book_Exchange.Management.Books;

import Book_Exchange.Books.BookControler;
import Book_Exchange.Books.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTests {


    @Autowired
    MockMvc mvc;


    @Test
    public void testgetAll()
    {
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.save(any())).then(i -> i.getArgument(0));

        BookControler bookControler = new BookControler(bookRepository);
        when(bookControler.getAllBooks()).then(i -> i.equals(bookRepository.findAll()));


    }

    @Test
    public void testAdd(){
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.save(any())).then(i -> i.getArgument(0));

        BookControler bookControler = new BookControler(bookRepository);
        when(bookControler.addBook(any())).then(i -> i.getArgument(0));
    }

    @Test
    public void testSearch(){
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.findOne(any())).then(i -> i.getArgument(0));

    }

    @Test
    public void testBorrowed(){

        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.findAll()).then(i -> i.getArgument(0));

    }



}
