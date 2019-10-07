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
    private static ArrayList<Item> books;
    private static ArrayList<Item> checkedOutBooks;

    private static ArrayList<Item> movies;


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

        movies = biblioteca.movies;
        movies.add(new Movie("Endgame", "2019", "Russo Brothers", "8.6"));
        movies.add(new Movie("IT 2", "2019", "Andy Muschietti", "6.9"));

        biblioteca.menu.add("List of Books");
        biblioteca.menu.add("Checkout a book");

        checkedOutBooks.add(new Book("A Game of Thrones", "GRRM", "1996"));
    }

    @Test
    public void testListOfBooksPrinted() {
        biblioteca.displayItem(books);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        biblioteca.displayItem(books);
        assertThat(byteArrayOutputStream.toString(), containsString("Harry Potter and the Chamber of Secrets || 1998 || JK Rowling"));
    }

    @Test
    public void testListOfMoviesPrinted() {
        biblioteca.displayItem(movies);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
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
    public void testListOfMoviesMenuOption(){
        biblioteca.parseInput(4);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
    }

    @Test
    public void testNegativeNumbersForMenuOption() {
        biblioteca.parseInput(-1);
        assertThat(byteArrayOutputStream.toString(), containsString("Please select a valid option:"));
    }

    @Test
    public void testDisplayBooks() {
        biblioteca.displayItem(books);
        assertThat(byteArrayOutputStream.toString(), containsString("Harry Potter and the Chamber of Secrets || 1998 || JK Rowling"));
    }

    @Test
    public void testDisplayMovies() {
        biblioteca.displayItem(movies);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
    }

    @Test
    public void testBookRemoved() {
        int numOfBooks = books.size();
        biblioteca.validItemSelected(0, books, checkedOutBooks);
        assertThat(books.size(), is(equalTo(numOfBooks-1)));
    }

    @Test
    public void testNegativeNumberForItemCheckOut() {
        biblioteca.validItemSelected(-1, books, checkedOutBooks);
        assertFalse(false);
    }

    @Test
    public void testHigherNumberOfItemsForCheckOut() {
        biblioteca.validItemSelected(books.size()+1, books, checkedOutBooks);
        assertFalse(false);
    }

    @Test
    public void testItemAdded() {
        int numOfCheckedOutBooks = checkedOutBooks.size();
        biblioteca.validItemSelected(1, books, checkedOutBooks );
        assertThat(checkedOutBooks.size(), is(equalTo(numOfCheckedOutBooks+1)));
    }

}

