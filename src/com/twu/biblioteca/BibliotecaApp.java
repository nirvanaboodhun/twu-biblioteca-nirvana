package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {

        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.printWelcomeMessage();

        Menu menu = biblioteca.setUpMenu();

        ItemList bookList = biblioteca.setUpBookList();
        menu.bookList = bookList;

        ItemList movieList = biblioteca.setUpMovieList();
        menu.movieList = movieList;

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
        menuOptions.add("List of movies");
        menuOptions.add("Check out a movie");
        return new Menu(menuOptions);
    }

    final ItemList setUpBookList() {
        ArrayList<Item> booksInLibrary = new ArrayList<Item>();
        booksInLibrary.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        booksInLibrary.add(new Book("1984", "George Orwell", "1949"));
        ArrayList<Item> checkedOutBooks = new ArrayList<Item>();
       return new ItemList(booksInLibrary, checkedOutBooks);
    }

    final ItemList setUpMovieList() {
        ArrayList<Item> moviesInLibrary = new ArrayList<Item>();
        moviesInLibrary.add(new Movie("Endgame", "2019", "Russo Brothers", "8.6"));
        moviesInLibrary.add(new Movie("IT 2", "2019", "Andy Muschietti", "6.9"));

        ArrayList<Item> checkedOutMovies = new ArrayList<Item>();
       return new ItemList(moviesInLibrary, checkedOutMovies);
    }


}
