package Book_Exchange.Borrow;

import Book_Exchange.Books.Book;
import org.springframework.data.repository.CrudRepository;


/**
 * This is the {@link BorrowRepository} interface where the borrowed {@link Book}  are saved.
 */
public interface BorrowRepository extends CrudRepository<Borrow,Long> {


}
