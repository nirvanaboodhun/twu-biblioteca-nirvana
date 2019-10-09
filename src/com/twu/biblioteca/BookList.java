package com.twu.biblioteca;

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
        if (books.size() == 0) {
            System.out.println("Sorry, there are currently no books to choose from");
        } else {
            for (Book book : books) {
                System.out.println(i + ": " + book.toString());
                i++;
            }
        }
    }

    final void checkoutBook() {
        display(booksInLibrary);
        if (booksInLibrary.size() > 0){
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
        }
    }

    private void printErrorMessage() {
        System.out.println("Please select a valid option:");
    }

    final void returnBook() {
        display(checkedOutBooks);
        if (checkedOutBooks.size() > 0) {
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

}
