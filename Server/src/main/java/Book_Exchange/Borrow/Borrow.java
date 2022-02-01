package Book_Exchange.Borrow;

import org.salespointframework.useraccount.UserAccount;

import javax.persistence.*;

@Entity
@Table(name="borrow")
public class Borrow {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String isbn;
    private String author;
    private int number;

    @ManyToOne
    private UserAccount userAccount;

    @SuppressWarnings("unused")
    private Borrow() {}

    public Borrow(String name, String isbn, String author, int number){

        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.number = number;
    }


    @Column(name="ID", nullable = false)
    public Long getId(){
        return this.id;
    }

    @Column(name="TITLE", nullable = false)
    public String getName(){
        return this.name;
    }

    @Column(name="ISBN", nullable = false)
    public String getIsbn(){
        return this.isbn;
    }

    @Column(name="AUTHOR", nullable = false)
    public String getAuthor(){
        return this.author;
    }

    @Column(name="NUMBER", nullable = false)
    public int getNumber() {return this.number;}

    public void updateNumber(int nr) {this.number=this.number-nr;}

    public void setNumber(int nr) {this.number = nr;}
}
