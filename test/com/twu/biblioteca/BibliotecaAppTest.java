package com.twu.biblioteca;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static BibliotecaApp biblioteca;
    private static ArrayList<Book> books;
    private static ArrayList<Book> checkedOutBooks;


    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        biblioteca = new BibliotecaApp();

        books = biblioteca.books;
        checkedOutBooks = biblioteca.checkedOutBooks;
        books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        books.add(new Book("1984", "George Orwell", "1949"));

        biblioteca.menu.add("List of Books");
        biblioteca.menu.add("Checkout a book");

        checkedOutBooks.add(new Book("A Game of Thrones", "GRRM", "1996"));
    }


    @Test
    public void testListOfBooksPrinted() {
        biblioteca.displayBooks(books);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        biblioteca.displayBooks(books);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets || JK Rowling || 1998"), containsString("1984 || George Orwell || 1949")));
    }

    @Test
    public void testMenuOptionsPrinted(){
        biblioteca.printMenuOptions();
        assertThat(byteArrayOutputStream.toString(), containsString("List of Books"));
    }

    @Test
    public void testListOfBooksMenuOption(){
        biblioteca.parseInput(1);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testNegativeNumbersForMenuOption() {
        biblioteca.parseInput(-1);
        assertThat(byteArrayOutputStream.toString(), containsString("Please select a valid option:"));
    }

    @Test
    public void testBookRemoved() {
        int numOfBooks = books.size();
        biblioteca.validBookSelected(0, books, checkedOutBooks);
        assertThat(books.size(), is(equalTo(numOfBooks-1)));
    }

    @Test
    public void testNegativeNumberForBookCheckOut() {
        biblioteca.validBookSelected(-1, books, checkedOutBooks);
        assertFalse(false);
    }

    @Test
    public void testHigherNumberOfBooksForCheckOut() {
        biblioteca.validBookSelected(books.size()+1, books, checkedOutBooks);
        assertFalse(false);
    }

    @Test
    public void testBookAdded() {
        int numOfCheckedOutBooks = checkedOutBooks.size();
        biblioteca.validBookSelected(1, books, checkedOutBooks );
        assertThat(checkedOutBooks.size(), is(equalTo(numOfCheckedOutBooks+1)));
    }
    
}

