package com.twu.biblioteca;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static BibliotecaApp biblioteca;

    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        biblioteca = new BibliotecaApp();

        biblioteca.books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        biblioteca.books.add(new Book("1984", "George Orwell", "1949"));

        biblioteca.menu.add("List of Books");
        biblioteca.menu.add("Checkout a book");
    }


    @Test
    public void testListOfBooksPrinted() {
        biblioteca.displayBooks();
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        biblioteca.displayBooks();
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
    public void testBookCheckOut() {
        int numOfBooks = biblioteca.books.size();
        biblioteca.validBookSelected(1);
        assertThat(biblioteca.books.size(), is(equalTo(numOfBooks-1)));
    }
}

