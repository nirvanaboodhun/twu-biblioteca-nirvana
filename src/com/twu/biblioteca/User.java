package com.twu.biblioteca;

import com.sun.tools.javac.jvm.Items;

import java.util.ArrayList;

public class User {
    String libraryNumber;
    String name;
    String email;
    String phoneNumber;
    String password;
    ArrayList<Item> checkedOutBooks = new ArrayList<Item>();
    ArrayList<Item> checkedOutMovies = new ArrayList<Item>();

    public User(String libraryNumber, String name, String email, String phoneNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Item> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void addToCheckedOutBooks(Item book) {
        checkedOutBooks.add(book);
    }

    public ArrayList<Item> getCheckedOutMovies() {
        return checkedOutMovies;
    }

    public void addToCheckedOutMovies(Item movie) {
        checkedOutMovies.add(movie);
    }

    @Override
    public String toString() {
        return "Name: " + name
                + ", Email: " + email
                + ", Phone number: " + phoneNumber;
    }


}
