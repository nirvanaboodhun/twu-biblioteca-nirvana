package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
    public static ArrayList<String> menu = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        menu.add("Exit");
        menu.add("List of Books");
        menu.add("Check out a book");


        books.add(new Book("Harry Potter and the Chamber of Secrets", "JK Rowling",
                "1998"));
        books.add(new Book("1984", "George Orwell", "1949"));

        printMenuOptions();

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

    static void printErrorMessage() {
        System.out.println("Please select a valid option:");
    }

    static void parseInput(int menuOption) {
        switch (menuOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                displayBooks();
                break;
            case 2:
                checkoutBook();
                break;
            default:
                printErrorMessage();
        }
    }

    static void displayBooks() {
        int i = 0;
        for (Book book : books) {
            System.out.println(i + ": " + book.toString());
            i++;
        }
    }

    static void checkoutBook() {
        displayBooks();
        System.out.println("\nPlease type in a number to select the book you would like to check out: ");

        boolean bookNotSelected = true;

        while (bookNotSelected) {
            Scanner input = new Scanner(System.in);
            int bookOption;
            try {
                bookOption = input.nextInt();
                if (validBookSelected(bookOption)) {
                    System.out.println("\nThank you! Enjoy the book!");
                    bookNotSelected = false;
                } else {
                    System.out.println("\nSorry, that book is not available");
                }
            } catch (Exception e) {
                printErrorMessage();
                displayBooks();
            }
        }
    }

    static boolean validBookSelected(int bookOption) {
        if (bookOption >= 0 && bookOption < books.size()) {
            Book selectedBook = books.get(bookOption);
            books.remove(selectedBook);
            checkedOutBooks.add(selectedBook);

            return true;
        } else return false;
    }
}
