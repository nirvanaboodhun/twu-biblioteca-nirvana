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

    final void checkoutitem() {
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
        if (i >= 0 && i < items.size()) {
            return true;
        } else return false;
    }

    final void moveItem(int i, ArrayList<Item> listToRemoveFrom,
                        ArrayList<Item> listToAddTo) {
        Item selectedItem = listToRemoveFrom.get(i);
        listToRemoveFrom.remove(selectedItem);
        listToAddTo.add(selectedItem);
    }

    final Boolean itemsAvailable(ArrayList<Item> items) {
        if (items.size() > 0) {
            return true;
        } else return false;
    }
}
