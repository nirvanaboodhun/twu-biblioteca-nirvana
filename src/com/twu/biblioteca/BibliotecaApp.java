package com.twu.biblioteca;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {

        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.printWelcomeMessage();

        Menu menu = biblioteca.setUpMenu();

        BookList bookList = biblioteca.setUpBookList();

        menu.bookList = bookList;

        menu.printMenuOptions();

        // loops until Exit option is selected
        while (true) {
            int optionSelected;
            optionSelected = biblioteca.getSelectedMenuOption(new Scanner(System.in));
            if (optionSelected != -1) {
                menu.parseInput(optionSelected);
            }
        }
    }

    final int getSelectedMenuOption(Scanner input) {
        try {
            System.out.println("\nPlease type in a number to select the corresponding menu option: ");
            return input.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    final void printWelcomeMessage() {
        String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

        System.out.println(welcomeMessage);
    }

    final Menu setUpMenu() {
        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("Exit");
        menuOptions.add("List of books");
        menuOptions.add("Check out a book");
        menuOptions.add("Return a book");
        return new Menu(menuOptions);
    }

    final BookList setUpBookList() {
        ArrayList<Book> booksInLibrary = new ArrayList<Book>();
        booksInLibrary.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        booksInLibrary.add(new Book("1984", "George Orwell", "1949"));
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
       return new BookList(booksInLibrary, checkedOutBooks);
    }


}
