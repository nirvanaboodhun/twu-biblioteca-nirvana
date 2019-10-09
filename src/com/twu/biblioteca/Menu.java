package com.twu.biblioteca;

import java.util.ArrayList;

public class Menu {
    ArrayList<String> menu = new ArrayList<String>();
    BookList bookList = new BookList();


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
                bookList.display(bookList.booksInLibrary);
                break;
            case 2:
                bookList.checkoutBook();
                break;
            case 3:
                bookList.returnBook();
                break;
            default:
                printErrorMessage();
        }
    }
}
