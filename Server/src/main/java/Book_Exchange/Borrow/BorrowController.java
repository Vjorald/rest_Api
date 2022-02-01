package Book_Exchange.Borrow;

import Book_Exchange.Books.Book;
import Book_Exchange.Books.BookControler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class BorrowController {


    @Autowired
    BookControler bookController;


    private final BorrowRepository borrowRepository;

    BorrowController(BorrowRepository borrowRepository){

        this.borrowRepository = borrowRepository;
    }


    /**
     *
     * @param titel     The title of the {@link Book} we need to borrow.
     * @param number    The number of the {@link Book} we need to borrow.
     */
    @PostMapping ("/borrow")
    String borrow(@RequestParam String titel, @RequestParam int number){

        if(!bookController.getByName(titel).isEmpty()) {
            for (Book book : bookController.getByName(titel)) {  //Finds the book we need to borrow

                if (book.getNumber() > number) {   //Controls the number of them

                    book.updateNumber(number);     //Changes the number of the remaining books in the library

                    Borrow b = new Borrow(book.getName(), book.getIsbn(),   //Creates a new instance of the same book with the number that we gave
                            book.getAuthor(), number);

                    for(Borrow borrow : borrowRepository.findAll()){ //If some books already borrowed
                        if(borrow.getName().equals(titel)) {
                            borrow.setNumber(number);
                            return "You borrowed your book(s)!";
                        }
                    }
                    borrowRepository.save(b);  //Saves the borrowed books in BorrowRepository

                    return "You borrowed your book(s)!";

                }
                else if (book.getNumber() == number){
                    for(Borrow borrow : borrowRepository.findAll()){ //If some books already borrowed
                        if(borrow.getName().equals(titel)) {
                            borrow.setNumber(number);
                            return "You borrowed your book(s)!";
                        }
                    }

                    Borrow b = new Borrow(book.getName(), book.getIsbn(),   //Creates a new instance of the same book with the number that we gave
                            book.getAuthor(), number);

                    borrowRepository.save(b);  //Saves the borrowed books in BorrowRepository

                    bookController.remove_Book(titel); //Removes the entity from the catalog

                    return "You borrowed your book(s)!";
                }

            }
        }

        return "There is no book with this title in the library or the number of them is not sufficient. Please check your input and try again!";

    }


    @PostMapping("/give_back")
    String giveBack(@RequestParam String titel, @RequestParam int number){


        for (Borrow borrow : borrowRepository.findAll()){    //Controlls which book we want to borrow
            if(borrow.getName().equals(titel) && borrow.getNumber() > number)  //Controlls the titel and number
            {

                borrow.updateNumber(number);        //Subtracts the number of the borrowed books with the number of the books we give back

               for(Book b : bookController.showAllBooks()) { //When there is already an antry of this book in the repository
                   if(b.getName().equals(titel)){
                       Book book = new Book(b.getName(), b.getIsbn(), b.getAuthor(), b.getNumber() + number); //A new instance with the whole amount of books
                       bookController.removeBook(b); //Removes temporarily the books from the library
                       bookController.addBook(book); //Enters the new instance - the book with the number of the in the library plus the number of borrowed and saves in BookRepository


                       return "You returned your book!";
                   }

               }

               Book book = new Book(borrow.getName(),borrow.getIsbn(),borrow.getAuthor(),borrow.getNumber());
               bookController.addBook(book);

                return "You returned your book!";
            }
            else if(borrow.getName().equals(titel) && borrow.getNumber() == number){

                Borrow borrow1 = null;

                for(Borrow b : borrowRepository.findAll()){
                    if(b.getName().equals(titel))
                        borrow1 = b;
                }

                for(Book b : bookController.getAllBooks()) {
                    if(b.getName().equals(titel)) {
                        Book book = new Book(b.getName(), b.getIsbn(), b.getAuthor(), b.getNumber() + number); //A new instance with the whole amount of books
                        bookController.removeBook(b); //Removes temporarily the books from the library
                        bookController.addBook(book); //Enters the new instance - the book with the number of the in the library plus the number of borrowed and saves in BookRepository

                        borrowRepository.delete(borrow1);

                        return "You returned your book!";
                    }
                }

                Book book = new Book(borrow1.getName(),borrow1.getIsbn(),borrow1.getAuthor(),borrow1.getNumber());
                bookController.addBook(book);
                borrowRepository.delete(borrow1);
                return "You returned your book!";
            }
        }

        return "It seems that the title you entered is not correct, or the number of them is invalid. Please check your input and try again!";
    }

}
