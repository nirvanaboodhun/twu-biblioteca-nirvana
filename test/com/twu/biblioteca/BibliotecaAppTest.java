package com.twu.biblioteca;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static PrintStream console;
    private static BibliotecaApp biblioteca;

    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(byteArrayOutputStream));
        biblioteca = new BibliotecaApp();
    }

    @Test
    public void testWelcomeMessagePrinted() {
        biblioteca.main(new String[]{});
        assertThat(byteArrayOutputStream.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!") );
    }

    @Test
    public void testListOfBooksPrinted() {
        biblioteca.books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        biblioteca.books.add(new Book("1984", "George Orwell", "1949"));

        biblioteca.main(new String[]{});
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
    }

    @Test
    public void testListOfBooksWithAuthorAndYearPublishedPrinted() {
        biblioteca.books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        biblioteca.books.add(new Book("1984", "George Orwell", "1949"));

        biblioteca.main(new String[]{});
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets || JK Rowling || 1998"), containsString("1984 || George Orwell || 1949")));
    }
}

