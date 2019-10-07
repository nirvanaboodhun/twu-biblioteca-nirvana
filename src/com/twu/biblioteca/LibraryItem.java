package com.twu.biblioteca;

import java.util.ArrayList;

public interface LibraryItem {
    void displayItem(ArrayList<Item> items);
    void checkoutItem();
    void returnItem();

}
