package com.twu.biblioteca;

import java.util.ArrayList;

public class Menu {
    ArrayList<String> menu;
    ItemList bookList = new ItemList();
    ItemList movieList = new ItemList();
    UserList userList = new UserList();
    public Menu(ArrayList<String> menu) {
        this.menu = menu;
    }

    public ArrayList<String> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<String> menu) {
        this.menu = menu;
    }

    final void printMenuOptions() {
        int i = 0;
        for (String menuOption : menu){
            System.out.println(i + ": " + menuOption);
            i++;
        }
    }

    final void printErrorMessage() {
        System.out.println("Please select a valid option:");
    }

    final void parseInput(int menuOption) {
        switch (menuOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                if (bookList.itemsAvailable(bookList.itemsInLibrary)) {
                    bookList.display(bookList.itemsInLibrary);
                } else {
                    System.out.println("Sorry, there are currently no books in the library");
                }
                break;
            case 2:
                if (UserList.userLoggedIn != null) {
                    bookList.checkoutItem();
                } else {
                    System.out.println("Please log in to check out a book");
                }
                break;
            case 3:
                bookList.returnItem();
                break;
            case 4:
                movieList.display(movieList.itemsInLibrary);
                break;
            case 5:
                movieList.checkoutItem();
                break;
            case 6:
                userList.logIn();
                break;
            default:
                printErrorMessage();
        }
    }
}
