package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    public static ArrayList<Item> books = new ArrayList<Item>();
    public static ArrayList<Item> checkedOutBooks = new ArrayList<Item>();
    public static ArrayList<String> menu = new ArrayList<String>();

    public static ArrayList<Item> movies = new ArrayList<Item>();
    public static ArrayList<Item> checkedOutMovies = new ArrayList<Item>();

    public static ArrayList<User> users = new ArrayList<User>();
    public static User userLoggedIn = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        menu.add("Exit");
        menu.add("List of books");
        menu.add("Check out a book");
        menu.add("Return a book");
        menu.add("List of movies");
        menu.add("Check out a movie");
        menu.add("Log in");
        menu.add("View checked out items");

        users.add(new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000"));

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
                checkoutItem(books, checkedOutBooks, "Book");
                break;
            case 3:
                returnItem(checkedOutBooks, books, "Book");
                break;
            case 4:
                displayItem(movies);
                break;
            case 5:
                checkoutItem(movies, checkedOutMovies, "Movie");
                break;
            case 6:
                logIn();
                break;
            case 7:
                viewCheckedOutItems();
                break;
            default:
                printErrorMessage();
        }
    }


    static boolean validItemSelected(int itemOption, ArrayList<Item> listToRemoveFrom,
                                     ArrayList<Item> listToAddTo, String itemType) {
        if (itemOption >= 0 && itemOption < listToRemoveFrom.size()) {
            Item selectedItem = listToRemoveFrom.get(itemOption);
            if (itemType.equals("Book")){
                userLoggedIn.addToCheckedOutBooks(selectedItem);
            } else{
                userLoggedIn.addToCheckedOutMovies(selectedItem);
            }
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

    private static void checkoutItem(ArrayList<Item> items, ArrayList<Item> checkedOutItems, String itemType) {
        if (userLoggedIn == null) {
            System.out.println("Please log in before you check out an item");
        } else {
            displayItem(items);
            if (items.size() > 0){
                System.out.println("\nPlease type in a number to select the items you would like to check out: ");

                boolean itemNotSelected = true;

                while (itemNotSelected) {
                    Scanner input = new Scanner(System.in);
                    int itemOption;
                    try {
                        itemOption = input.nextInt();
                        if (validItemSelected(itemOption, items, checkedOutItems, itemType)) {
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

    }

    private static void returnItem(ArrayList<Item> checkedOutItems, ArrayList<Item> items, String itemType) {
        if (userLoggedIn == null) {
            System.out.println("Please log in before you check out an item");
        } else {
            displayItem(checkedOutItems);
            if (checkedOutItems.size() > 0) {
                System.out.println("Please select a number corresponding to the book you would like to return:");

                boolean itemNotSelected = true;

                while (itemNotSelected) {
                    Scanner input = new Scanner(System.in);
                    int itemOption;
                    try {
                        itemOption = input.nextInt();
                        if (validItemSelected(itemOption, checkedOutItems, items, itemType)) {
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

    private static void logIn() {
        String libraryNumber = getLibraryNumber();
        if (!(libraryNumber.equals(""))) {
            if (validLibraryNumber(libraryNumber)) {
                userLoggedIn = findUserByLibraryNumber(libraryNumber);
                if (userLoggedIn != null) {
                    String password = getPassword();
                    if (!(checkPassword(userLoggedIn, password))) {
                        userLoggedIn = null;
                    }
                }
            }
        }
    }

    private static String getPassword() {
        System.out.println("Please enter your password");
        Scanner passwordInput = new Scanner(System.in);
        String inputPassword;
        try {
            inputPassword = passwordInput.next();
            return inputPassword;

        } catch (Exception e) {
            System.out.println("Invalid Password");
            userLoggedIn = null;
        }
        return "";
    }

    public static String getLibraryNumber() {
        System.out.println("Please enter your library number");

        Scanner input = new Scanner(System.in);
        String libraryNumber;

        try {
            libraryNumber = input.next();
            return libraryNumber;
        } catch (Exception e) {
            System.out.println("That is not a valid library number");
        }
        return "";
    }

    public static Boolean validLibraryNumber(String libraryNumber) {
        //https://stackoverflow.com/questions/18259644/how-to-check-if-a-string-matches-a-specific-format
        if (libraryNumber.matches("\\d{3}-\\d{4}")) {
            return true;
        }
        else {
            System.out.println("That is not a valid library number");
            return false;
        }
    }

    public static User findUserByLibraryNumber(String libraryNumber) {
        for (User user : users) {
            if (user.getLibraryNumber().equals(libraryNumber)) {
                return user;
            }
        }
        return null;
    }

    public static Boolean checkPassword(User user, String password) {
        if (user.getPassword().equals(password)) {
            user.toString();
            return true;
        } else {
            System.out.println("Invalid Password");
            return false;
        }
    }


    public static void viewCheckedOutItems() {
        if (userLoggedIn == null) {
            System.out.println("Please log in before you check out an item");
        } else {
            System.out.println("Checked Out Books: \n");
            for (Item book : userLoggedIn.checkedOutBooks) {
                System.out.println(book.toString() + "\n");
            }

            System.out.println("Checked Out Movies: \n");
            for (Item book : userLoggedIn.checkedOutMovies) {
                System.out.println(book.toString() + "\n");
            }
        }
    }
}
