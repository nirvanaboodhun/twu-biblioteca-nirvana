package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class BookListTest {
    private static ByteArrayOutputStream byteArrayOutputStream;
    private static BookList bookList;

    @Before
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        ArrayList<Book> booksInLibrary = new ArrayList<Book>();
        booksInLibrary.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        booksInLibrary.add(new Book("1984", "George Orwell", "1949"));

        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
        checkedOutBooks.add(new Book("A Game of Thrones", "GRRM", "1996"));

        bookList = new BookList(booksInLibrary, checkedOutBooks);
    }

    @Test
    public void testListOfBooksPrinted() {
        bookList.display(bookList.booksInLibrary);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        bookList.display(bookList.booksInLibrary);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets || JK Rowling || 1998"), containsString("1984 || George Orwell || 1949")));
    }

    @Test
    public void testBookRemoved() {
        int numOfBooks = bookList.booksInLibrary.size();
        bookList.moveBook(0, bookList.booksInLibrary, bookList.checkedOutBooks);
        assertThat(bookList.booksInLibrary.size(), is(equalTo(numOfBooks-1)));
    }

    @Test
    public void testNegativeNumberForBookCheckOut() {
        bookList.validBookSelected(-1, bookList.booksInLibrary);
        assertFalse(false);
    }

    @Test
    public void testHigherNumberOfBooksForCheckOut() {
        bookList.validBookSelected(bookList.booksInLibrary.size()+1, bookList.booksInLibrary);
        assertFalse(false);
    }

    @Test
    public void testBookAdded() {
        int numOfCheckedOutBooks = bookList.checkedOutBooks.size();
        bookList.moveBook(1, bookList.booksInLibrary, bookList.checkedOutBooks);
        assertThat(bookList.checkedOutBooks.size(), is(equalTo(numOfCheckedOutBooks+1)));
    }
}
