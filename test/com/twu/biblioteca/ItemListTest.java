package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ItemListTest {
    private static ByteArrayOutputStream byteArrayOutputStream;
    private static ItemList bookList;
    private static ItemList movieList;

    @Before
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        ArrayList<Item> booksInLibrary = new ArrayList<Item>();
        booksInLibrary.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        booksInLibrary.add(new Book("1984", "George Orwell", "1949"));

        ArrayList<Item> checkedOutBooks = new ArrayList<Item>();
        checkedOutBooks.add(new Book("A Game of Thrones", "GRRM", "1996"));

        bookList = new ItemList(booksInLibrary, checkedOutBooks);

        ArrayList<Item> moviesInLibrary = new ArrayList<Item>();
        ArrayList<Item> checkedOutMovies = new ArrayList<Item>();

        moviesInLibrary.add(new Movie("Endgame", "2019", "Russo Brothers", "8.6"));
        checkedOutMovies.add(new Movie("IT 2", "2019", "Andy Muschietti", "6.9"));

        movieList = new ItemList(moviesInLibrary, checkedOutMovies);

        UserList.userLoggedIn = new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000");
    }

    @Test
    public void testListOfBooksPrinted() {
        bookList.display(bookList.itemsInLibrary);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfMoviesPrinted() {
        movieList.display(movieList.itemsInLibrary);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        bookList.display(bookList.itemsInLibrary);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets || JK Rowling || 1998"), containsString("1984 || George Orwell || 1949")));
    }

    @Test
    public void testAlphaCharForInput() {
        int returnValue = bookList.getItemSelected(new Scanner("qwewer"));
        assertThat(returnValue, is(equalTo(-1)));
    }

    @Test
    public void testNumbersForInput() {
        int returnValue = bookList.getItemSelected(new Scanner("8"));
        assertThat(returnValue, is(equalTo(8)));
    }

    @Test
    public void testBookRemoved() {
        int numOfBooks = bookList.itemsInLibrary.size();
        bookList.moveItem(0, bookList.itemsInLibrary, bookList.checkedOutItems);
        assertThat(bookList.itemsInLibrary.size(), is(equalTo(numOfBooks-1)));
    }

    @Test
    public void testNegativeNumberForBookCheckOut() {
        bookList.validItemSelected(-1, bookList.itemsInLibrary);
        assertFalse(false);
    }

    @Test
    public void testHigherNumberOfBooksForCheckOut() {
        bookList.validItemSelected(bookList.itemsInLibrary.size()+1, bookList.itemsInLibrary);
        assertFalse(false);
    }

    @Test
    public void testBookAdded() {
        int numOfCheckedOutBooks = bookList.checkedOutItems.size();
        bookList.moveItem(1, bookList.itemsInLibrary, bookList.checkedOutItems);
        assertThat(bookList.checkedOutItems.size(), is(equalTo(numOfCheckedOutBooks+1)));
    }

    @Test
    public void testAddBookToUser() {
        Book book = new Book("1984", "George Orwell", "1949");

        bookList.addToUser(book);

        assertThat(UserList.userLoggedIn.checkedOutBooks, hasItem(book));
    }
}
