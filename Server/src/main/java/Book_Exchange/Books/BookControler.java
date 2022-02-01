package Book_Exchange.Books;

import Book_Exchange.Borrow.Borrow;
import Book_Exchange.Borrow.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookControler {

    private final BookRepository bookRepository;

    @Autowired
    BorrowRepository borrowRepository;

    public BookControler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     *
     * @return A list of all the books in the library
     */
    @GetMapping("/all")
    public List<Book> showAllBooks()
    {

        return getAllBooks();
    }


    /**
     *
     * @param title     The title of the {@link Book} we want to add.
     * @param isbn      The isbn of the {@line Book} we want to add.
     * @param author    The author of the {@link Book} we want to add.
     * @param number    The number of the same {@link Book}s we want to add.
     */
    @PostMapping("/add")
    public String add_book(@RequestParam String title, @RequestParam String isbn, @RequestParam String author, @RequestParam int number){
        Book b = new Book(title,isbn,author,number);
        addBook(b);

        return "The book was added to the catalog!";
    }

    /**
     *
     * @param searchTerm The Matching-Word that is used to search for books in the library.
     * @return A list of books that match the searching word.
     */
    @PostMapping("/search")
    public String searchTerm(@RequestParam String searchTerm){ //Finds and shows a particular book that the client searches by name

        if(!getByTerm(searchTerm).isEmpty()){
            String a = "<table><tr><th>Titel</th><th>ISBN</th><th>Author</th><th>Number</th></tr>";

            for(Book book : getByTerm(searchTerm)){
                a = a + "<tr>";
                a = a + "<td>" + book.getName() + "</td>";
                a = a + "<td>" + book.getIsbn() + "</td>";
                a = a + "<td>" + book.getAuthor() + "</td>";
                a = a + "<td>" + book.getNumber() + "</td>";
            }
            a = a + "</table>";
            return a;
        }

        return "There are unfortunately no results according to the given term! Please try again!";
    }

    /**
     *
     * @return A list of all the borrowed books.
     */
    @GetMapping("/borrowed")
    public List<Borrow> borrowedBooks(){
        List<Borrow> borrow = new ArrayList<>();
        for(Borrow b : borrowRepository.findAll()){
            borrow.add(b);
        }
        return borrow;
    }


    /**
     *
     * @param title The titel of the {@link Book} we want to remove.
     */
    @PostMapping("/remove")
    public String remove_Book(@RequestParam String title){

        for(Book book : bookRepository.findAll()){
            if(book.getName().equals(title)) {
                removeBook(book);

                return "The book was removed from the catalog!";
            }
        }

        return "There is no book with this title in the library! Please check your input and try again!";

    }

    /**
     *
     * @param Id The id of the book.
     * @return A {@link Book} that contains the given id.
     */
    public Optional<Book> findById(Long Id){
        return bookRepository.findById(Id);
    }


    /**
     *
     * @return An {@link ArrayList} where all {@link Book} are saved.
     */
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();

        for (Book b : bookRepository.findAll())
            books.add(b);

        return books;
    }


    /**
     *
     * @param name The title of the {@link Book} we need.
     * @return An {@link ArrayList} with all the {@link Book} that contain the given {@param name}.
     */
    public ArrayList<Book> getByName (String name){ //Returns an arraylist with all the books that have the given name
        ArrayList<Book> books = new ArrayList<Book>();

        for (Book b : bookRepository.findAll()){
            if(b.getName().equals(name))
                books.add(b);
        }

        return books;
    }


    /**
     *
     * @param isbn The isbn of the {@link Book} we need.
     * @return An {@link ArrayList} with all the {@link Book} that contain the given {@param isbn}.
     */
    public ArrayList<Book> getByISBN (String isbn){ //Returns an arraylist with all the books that have the given isbn
        ArrayList<Book> books = new ArrayList<Book>();

        for (Book b : bookRepository.findAll()){
            if(b.getIsbn().equals(isbn))
                books.add(b);
        }

        return books;
    }


    /**
     *
     * @param author The author of the {@link Book} we need.
     * @return An {@link ArrayList} with all the {@link Book} that contain the given {@param author}.
     */
    public ArrayList<Book> getByAuthor (String author){ //Returns an arraylist with all the books that have the given author
        ArrayList<Book> books = new ArrayList<Book>();

        for (Book b : bookRepository.findAll()){
            if(b.getAuthor().equals(author))
                books.add(b);
        }

        return books;
    }


    /**
     *
     * @param searchTerm The term that matches with our {@link Book}.
     * @return An {@link ArrayList} with all the {@link Book} that contain the given {@param searchTerm}.
     */
    public ArrayList<Book> getByTerm (String searchTerm){ //Returns an arraylist with all the books that have the given author
        ArrayList<Book> books = new ArrayList<Book>();

        for (Book b : bookRepository.findAll()){
            if(b.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                books.add(b);
            } else if (b.getIsbn().toLowerCase().contains(searchTerm.toLowerCase())) {
                books.add(b);
            } else if (b.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                books.add(b);
            }
        }

        return books;
    }



    /**
     *
     * @param b The given {@link Book} we want to save in the {@link BookRepository}
     * @return Saves the {@link Book} in the {@link BookRepository}
     */
    public Book addBook(Book b) {return bookRepository.save(b);} //Used to save a particular book in the repository.


    /**
     *
     * @param b The {@link Book} we want to remove from the library.
     */
    public void removeBook(Book b){
        bookRepository.delete(b);
    }



}
