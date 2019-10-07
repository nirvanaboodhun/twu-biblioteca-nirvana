package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    public static ArrayList<Item> books = new ArrayList<Item>();
    public static ArrayList<Item> checkedOutBooks = new ArrayList<Item>();
    public static ArrayList<String> menu = new ArrayList<String>();

    public static ArrayList<Item> movies = new ArrayList<Item>();
    public static ArrayList<Item> checkedOutMovies = new ArrayList<Item>();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        menu.add("Exit");
        menu.add("List of books");
        menu.add("Check out a book");
        menu.add("Return a book");
        menu.add("List of movies");
        menu.add("Check out a movie");


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
                checkoutItem(books, checkedOutBooks);
                break;
            case 3:
                returnItem(checkedOutBooks, books);
                break;
            case 4:
                displayItem(movies);
                break;
            case 5:
                checkoutItem(movies, checkedOutMovies);
                break;
            default:
                printErrorMessage();
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

    private static void checkoutItem(ArrayList<Item> items, ArrayList<Item> checkedOutItems) {
        displayItem(items);
        if (items.size() > 0){
            System.out.println("\nPlease type in a number to select the items you would like to check out: ");

            boolean itemNotSelected = true;

            while (itemNotSelected) {
                Scanner input = new Scanner(System.in);
                int itemOption;
                try {
                    itemOption = input.nextInt();
                    if (validItemSelected(itemOption, items, checkedOutItems)) {
                        System.out.println("\nThank you! Enjoy!");
                        itemNotSelected = false;
                    } else {
                        System.out.println("\nSorry, that item is not available");
                        itemNotSelected = false;
                    }
                } catch (Exception e) {
                    printErrorMessage();
                    displayItem(items);
                }
            }
        }
    }

    private static void returnItem(ArrayList<Item> checkedOutItems, ArrayList<Item> items) {
        displayItem(checkedOutItems);
        if (checkedOutItems.size() > 0) {
            System.out.println("Please select a number corresponding to the book you would like to return:");

            boolean itemNotSelected = true;

            while (itemNotSelected) {
                Scanner input = new Scanner(System.in);
                int itemOption;
                try {
                    itemOption = input.nextInt();
                    if (validItemSelected(itemOption, checkedOutItems, items)) {
                        System.out.println("\nThank you for returning the book");
                        itemNotSelected = false;
                    } else {
                        System.out.println("\nThat is not a valid book to return");
                        itemNotSelected = false;
                    }
                } catch (Exception e) {
                    printErrorMessage();
                    displayItem(checkedOutItems);
                }
            }
        }
    }
}
