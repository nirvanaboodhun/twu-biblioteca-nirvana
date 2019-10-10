package com.twu.biblioteca;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Scanner;

public class BookList {

    ArrayList<Book> booksInLibrary = new ArrayList<Book>();
    ArrayList<Book> checkedOutBooks = new ArrayList<Book>();


    public BookList() {
    }

    public BookList(ArrayList<Book> booksInLibrary, ArrayList<Book> checkedOutBooks) {
        this.booksInLibrary = booksInLibrary;
        this.checkedOutBooks = checkedOutBooks;
    }

    public ArrayList<Book> getBooksInLibrary() {
        return booksInLibrary;
    }

    public void setBooksInLibrary(ArrayList<Book> booksInLibrary) {
        this.booksInLibrary = booksInLibrary;
    }

    public ArrayList<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    final void display(ArrayList<Book> books) {
        int i = 0;
        for (Book book : books) {
            System.out.println(i + ": " + book.toString());
            i++;
        }
    }

    final void checkoutBook() {
        if (booksAvailable(booksInLibrary)) {
            display(booksInLibrary);
            System.out.println("\nPlease type in a number to select the book you would like to check out: ");

            boolean bookNotSelected = true;

            while (bookNotSelected) {
                Scanner input = new Scanner(System.in);
                int bookOption;
                try {
                    bookOption = input.nextInt();
                    if (validBookSelected(bookOption, booksInLibrary)) {
                        moveBook(bookOption, booksInLibrary, checkedOutBooks);
                        System.out.println("\nThank you! Enjoy the book!");
                        bookNotSelected = false;
                    } else {
                        System.out.println("\nSorry, that book is not available");
                        bookNotSelected = false;
                    }
                } catch (Exception e) {
                    printErrorMessage();
                    display(booksInLibrary);
                }
            }
        } else {
            System.out.println("Sorry, there are no books available to check out");
        }
    }

    private void printErrorMessage() {
        System.out.println("Please select a valid option:");
    }

    final void returnBook() {
        if (booksAvailable(checkedOutBooks)) {
            display(checkedOutBooks);
            System.out.println("Please select a number corresponding to the book you would like to return:");

            boolean bookSelected = false;

            while (!(bookSelected)) {
                Scanner input = new Scanner(System.in);
                int bookOption;
                try {
                    bookOption = input.nextInt();
                    if (validBookSelected(bookOption, checkedOutBooks)) {
                        moveBook(bookOption, checkedOutBooks, booksInLibrary);
                        System.out.println("\nThank you for returning the book");
                        bookSelected = true;
                    } else {
                        System.out.println("\nThat is not a valid book to return");
                        bookSelected = true;
                    }
                } catch (Exception e) {
                    printErrorMessage();
                    display(booksInLibrary);
                }
            }
        } else {
            System.out.println("There are no books to return");
        }
    }

    final boolean validBookSelected(int bookOption, ArrayList<Book> books) {
        if (bookOption >= 0 && bookOption < books.size()) {
            return true;
        } else return false;
    }

    final void moveBook(int bookOption, ArrayList<Book> listToRemoveFrom,
                        ArrayList<Book> listToAddTo) {
        Book selectedBook = listToRemoveFrom.get(bookOption);
        listToRemoveFrom.remove(selectedBook);
        listToAddTo.add(selectedBook);
    }

    final Boolean booksAvailable(ArrayList<Book> books) {
        if (books.size() > 0) {
            return true;
        } else return false;
    }
}
