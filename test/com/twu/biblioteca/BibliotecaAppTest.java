package com.twu.biblioteca;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BibliotecaAppTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static ArrayList<Item> books;
    private static ArrayList<Item> checkedOutBooks;
    private static ArrayList<Item> movies;

    private static ArrayList<User> users;


    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        books = BibliotecaApp.books;
        checkedOutBooks = BibliotecaApp.checkedOutBooks;
        books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        books.add(new Book("1984", "George Orwell", "1949"));

        movies = BibliotecaApp.movies;
        movies.add(new Movie("Endgame", "2019", "Russo Brothers", "8.6"));
        movies.add(new Movie("IT 2", "2019", "Andy Muschietti", "6.9"));

        BibliotecaApp.menu.add("List of Books");
        BibliotecaApp.menu.add("Checkout a book");

        checkedOutBooks.add(new Book("A Game of Thrones", "GRRM", "1996"));

        users = BibliotecaApp.users;
        users.add(new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000"));
    }

    @Test
    public void testListOfBooksPrinted() {
        BibliotecaApp.displayItem(books);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        BibliotecaApp.displayItem(books);
        assertThat(byteArrayOutputStream.toString(), containsString("Harry Potter and the Chamber of Secrets || 1998 || JK Rowling"));
    }

    @Test
    public void testListOfMoviesPrinted() {
        BibliotecaApp.displayItem(movies);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
    }

    @Test
    public void testMenuOptionsPrinted(){
        BibliotecaApp.printMenuOptions();
        assertThat(byteArrayOutputStream.toString(), containsString("List of Books"));
    }

    @Test
    public void testListOfBooksMenuOption(){
        BibliotecaApp.parseInput(1);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfMoviesMenuOption(){
        BibliotecaApp.parseInput(4);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
    }

    @Test
    public void testNegativeNumbersForMenuOption() {
        BibliotecaApp.parseInput(-1);
        assertThat(byteArrayOutputStream.toString(), containsString("Please select a valid option:"));
    }

    @Test
    public void testDisplayBooks() {
        BibliotecaApp.displayItem(books);
        assertThat(byteArrayOutputStream.toString(), containsString("Harry Potter and the Chamber of Secrets || 1998 || JK Rowling"));
    }

    @Test
    public void testDisplayMovies() {
        BibliotecaApp.displayItem(movies);
        assertThat(byteArrayOutputStream.toString(), containsString("Endgame || Russo Brothers || 8.6 || 2019"));
    }

    @Test
    public void testBookRemoved() {
        int numOfBooks = books.size();
        BibliotecaApp.validItemSelected(0, books, checkedOutBooks);
        assertThat(books.size(), is(equalTo(numOfBooks-1)));
    }

    @Test
    public void testNegativeNumberForItemCheckOut() {
        BibliotecaApp.validItemSelected(-1, books, checkedOutBooks);
        assertFalse(false);
    }

    @Test
    public void testHigherNumberOfItemsForCheckOut() {
        BibliotecaApp.validItemSelected(books.size()+1, books, checkedOutBooks);
        assertFalse(false);
    }

    @Test
    public void testItemAdded() {
        int numOfCheckedOutBooks = checkedOutBooks.size();
        BibliotecaApp.validItemSelected(1, books, checkedOutBooks );
        assertThat(checkedOutBooks.size(), is(equalTo(numOfCheckedOutBooks+1)));
    }

    @Test
    public void testFindUserByLibraryNumber() {
        User user = BibliotecaApp.findUserByLibraryNumber("123-4567");
        assertThat(user.getName(), is(equalTo("John Doe")));
    }

    @Test
    public void testValidLibraryNumber() {
        Boolean libraryNumberCheck = BibliotecaApp.validLibraryNumber("123-4567");
        assertThat(libraryNumberCheck, is(equalTo(true)));
    }

    @Test
    public void testInvalidLibraryNumber() {
        Boolean libraryNumberCheck = BibliotecaApp.validLibraryNumber("1234567");
        assertThat(libraryNumberCheck, is(equalTo(false)));
    }

    @Test
    public void testInvalidLibraryNumberWithChar() {
        Boolean libraryNumberCheck = BibliotecaApp.validLibraryNumber("qwerty");
        assertThat(libraryNumberCheck, is(equalTo(false)));
    }


    @Test
    public void testCheckPassword() {
        User user = new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000");
        Boolean passwordCheck = BibliotecaApp.checkPassword(user, "0000");
        assertThat(passwordCheck, is(equalTo(true)));
    }

    @Test
    public void testInvalidPassword() {
        User user = new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000");
        Boolean passwordCheck = BibliotecaApp.checkPassword(user, "2222");
        assertThat(passwordCheck, is(equalTo(false)));
    }

}

