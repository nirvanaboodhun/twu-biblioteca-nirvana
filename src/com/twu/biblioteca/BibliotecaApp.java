package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    public static ArrayList<Item> books = new ArrayList<Item>();
    public static ArrayList<Item> checkedOutBooks = new ArrayList<Item>();
    public static ArrayList<String> menu = new ArrayList<String>();

    public static ArrayList<Item> movies = new ArrayList<Item>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        menu.add("Exit");
        menu.add("List of books");
        menu.add("Check out a book");
        menu.add("Return a book");
        menu.add("List of movies");

        books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        books.add(new Book("1984", "George Orwell", "1949"));

        movies.add(new Movie("Endgame", "2019", "Russo Brothers", "8.6"));
        movies.add(new Movie("IT 2", "2019", "Andy Muschietti", "6.9"));
        printMenuOptions();

        // loops until Exit option is selected
        while (true) {
            Scanner input = new Scanner(System.in);
            int menuOption;
            try {
                System.out.println("\nPlease type in a number to select the corresponding menu option: ");
                menuOption = input.nextInt();
                parseInput(menuOption);
            } catch (Exception e) {
                printErrorMessage();
                printMenuOptions();
            }
        }
    }

    static void printMenuOptions() {
        int i = 0;
        for (String menuOption : menu){
            System.out.println(i + ": " + menuOption);
            i++;
        }
    }

    private static void printErrorMessage() {
        System.out.println("Please select a valid option:");
    }

    static void parseInput(int menuOption) {
        switch (menuOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                displayItem(books);
                break;
            case 2:
                checkoutBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                displayItem(movies);
                break;
            default:
                printErrorMessage();
        }
    }
//
//    static void displayBooks(ArrayList<Book> books) {
//        int i = 0;
//        if (books.size() == 0) {
//            System.out.println("Sorry, there are currently no books to choose from");
//        } else {
//            for (Book book : books) {
//                System.out.println(i + ": " + book.toString());
//                i++;
//            }
//        }
//    }

    static void checkoutBook() {
        displayItem(books);
        if (books.size() > 0){
            System.out.println("\nPlease type in a number to select the book you would like to check out: ");

            boolean bookNotSelected = true;

            while (bookNotSelected) {
                Scanner input = new Scanner(System.in);
                int bookOption;
                try {
                    bookOption = input.nextInt();
                    if (validItemSelected(bookOption, books, checkedOutBooks)) {
                        System.out.println("\nThank you! Enjoy the book!");
                        bookNotSelected = false;
                    } else {
                        System.out.println("\nSorry, that book is not available");
                        bookNotSelected = false;
                    }
                } catch (Exception e) {
                    printErrorMessage();
                    displayItem(books);
                }
            }
        }
    }

    static void returnBook() {
        displayItem(checkedOutBooks);
        if (checkedOutBooks.size() > 0) {
            System.out.println("Please select a number corresponding to the book you would like to return:");

            boolean bookNotSelected = true;

            while (bookNotSelected) {
                Scanner input = new Scanner(System.in);
                int bookOption;
                try {
                    bookOption = input.nextInt();
                    if (validItemSelected(bookOption, checkedOutBooks, books)) {
                        System.out.println("\nThank you for returning the book");
                        bookNotSelected = false;
                    } else {
                        System.out.println("\nThat is not a valid book to return");
                        bookNotSelected = false;
                    }
                } catch (Exception e) {
                    printErrorMessage();
                    displayItem(books);
                }
            }
        }
    }

    static boolean validItemSelected(int itemOption, ArrayList<Item> listToRemoveFrom,
                                     ArrayList<Item> listToAddTo) {
        if (itemOption >= 0 && itemOption < listToRemoveFrom.size()) {
            Item selectedItem = listToRemoveFrom.get(itemOption);
            listToRemoveFrom.remove(selectedItem);
            listToAddTo.add(selectedItem);
            return true;
        } else return false;
    }

    public static void displayItem(ArrayList<Item> items) {
        int i = 0;
        if (items.size() == 0) {
            System.out.println("Sorry, there are currently no items to choose from");
        } else {
            for (Item item : items) {
                System.out.println(i + ": " + item.toString());
                i++;
            }
        }

    }

    public void checkoutItem() {

    }

    public void returnItem() {

    }
}
