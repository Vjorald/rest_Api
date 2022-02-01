package Book_Exchange.Management.Borrow;

import Book_Exchange.Books.BookRepository;
import Book_Exchange.Borrow.BorrowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BorrowController {

    @Autowired
    MockMvc mvc;

    @Test
    public void borrowTest(){

        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.getById(any())).then(i -> i.getArgument(0));

        BorrowRepository borrowRepository = mock(BorrowRepository.class);
        when(borrowRepository.save(any())).then(i -> i.getArgument(0));
    }

    @Test
    public void returnTest(){

        BorrowRepository borrowRepository = mock(BorrowRepository.class);
        when(borrowRepository.findById(any())).then(i -> i.getArgument(0));

        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.save(any())).then(i -> i.getArgument(0));
    }
}
