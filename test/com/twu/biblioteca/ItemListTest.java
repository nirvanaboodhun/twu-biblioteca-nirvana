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
    }

    @Test
    public void testListOfBooksPrinted() {
        bookList.display(bookList.itemsInLibrary);
        assertThat(byteArrayOutputStream.toString(), allOf(containsString("Harry Potter and the Chamber of Secrets"), containsString("1984")));
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
}
