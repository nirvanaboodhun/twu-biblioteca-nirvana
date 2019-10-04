package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotecaApp {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<String> menu = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        menu.add("0.Exit");
        menu.add("1.List of Books");


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
                handleErrors();
            }
        }

    }


    static void printMenuOptions() {
        for (String menuOption : menu){
            System.out.println(menuOption);
        }
    }

    static void handleErrors() {
        System.out.println("Please select a valid option:");
        printMenuOptions();
    }

    static void parseInput(int menuOption) {
        if (menuOption == 0) {
            System.exit(0);
        } else if (menuOption == 1) {
            displayBooks();
        } else if (menuOption >= menu.size() || menuOption < 0) {
            handleErrors();
        }
    }

    static void displayBooks() {
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
}
