package com.twu.biblioteca;


import java.util.ArrayList;
import java.util.Scanner;

public class ItemList {

    ArrayList<Item> itemsInLibrary = new ArrayList<Item>();
    ArrayList<Item> checkedOutItems = new ArrayList<Item>();


    public ItemList() {
    }

    public ItemList(ArrayList<Item> itemsInLibrary, ArrayList<Item> checkedOutItems) {
        this.itemsInLibrary = itemsInLibrary;
        this.checkedOutItems = checkedOutItems;
    }

    final void display(ArrayList<Item> items) {
        int i = 0;
        for (Item item : items) {
            System.out.println(i + ": " + item.toString());
            i++;
        }
    }

    final void checkoutItem() {
        if (itemsAvailable(itemsInLibrary)) {
            display(itemsInLibrary);

            boolean itemNotSelected = true;

            while (itemNotSelected) {
                int itemOption = getItemSelected(new Scanner(System.in));
                if (itemOption >= 0) {
                    if (validItemSelected(itemOption, itemsInLibrary)) {
                        moveItem(itemOption, itemsInLibrary, checkedOutItems);
                        System.out.println("\nThank you! Enjoy the item!");
                        itemNotSelected = false;
                    } else {
                        System.out.println("\nSorry, that item is not available");
                        itemNotSelected = false;
                    }
                }
                display(itemsInLibrary);
            }
        } else {
            System.out.println("Sorry, there are no items available to check out");
        }
    }

    final int getItemSelected(Scanner input) {
        try {
            System.out.println("\nPlease type in a number to select a item");
            return input.nextInt();
        } catch (Exception e) {
            printErrorMessage();
            return -1;
        }
    }

    private void printErrorMessage() {
        System.out.println("Please select a valid option:");
    }

    final void returnItem() {
        if (itemsAvailable(checkedOutItems)) {
            display(checkedOutItems);
            boolean itemSelected = false;

            while (!(itemSelected)) {
                int itemOption = getItemSelected(new Scanner(System.in));

                    if (itemOption >= 0) {
                        if (validItemSelected(itemOption, checkedOutItems)) {
                            moveItem(itemOption, checkedOutItems, itemsInLibrary);
                            System.out.println("\nThank you for returning the item");
                            itemSelected = true;
                        } else {
                            System.out.println("\nThat is not a valid item to return");
                            itemSelected = true;
                        }
                    }
                    display(checkedOutItems);
            }
        } else {
            System.out.println("There are no items to return");
        }
    }

    final boolean validItemSelected(int i, ArrayList<Item> items) {
        return i >= 0 && i < items.size();
    }

    final void moveItem(int i, ArrayList<Item> listToRemoveFrom,
                        ArrayList<Item> listToAddTo) {
        Item selectedItem = listToRemoveFrom.get(i);
        addToUser(selectedItem);
        listToRemoveFrom.remove(selectedItem);
        listToAddTo.add(selectedItem);
    }

    final void addToUser(Item selectedItem) {
        if (selectedItem instanceof Book) {
            UserList.userLoggedIn.addToCheckedOutBooks(selectedItem);
        } else {
            UserList.userLoggedIn.addToCheckedOutMovies(selectedItem);
        }
    }

    final Boolean itemsAvailable(ArrayList<Item> items) {
        return items.size() > 0;
    }
}
