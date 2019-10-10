package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    ArrayList<User> users = new ArrayList<User>();
    public static User userLoggedIn;

    public UserList() {
    }

    public UserList(ArrayList<User> users) {
        this.users = users;
    }

    final User logIn() {
        userLoggedIn = null;
        String libraryNumber = getLibraryNumber();
        if (!(libraryNumber.equals(""))) {
            if (validLibraryNumber(libraryNumber)) {
                userLoggedIn = findUserByLibraryNumber(libraryNumber);
                if (userLoggedIn != null) {
                    String password = getPassword();
                    if (validPassword(userLoggedIn, password)) {
                        return userLoggedIn;
                    }
                }
            }
        }
        return userLoggedIn;
    }

    private String getPassword() {
        System.out.println("Please enter your password");
        return getUserInput(new Scanner(System.in));
    }

    private String getLibraryNumber() {
        System.out.println("Please enter your library number");
        String libraryNumber;
        libraryNumber = getUserInput(new Scanner(System.in));
        return libraryNumber;
    }

    private String getUserInput(Scanner input) {
        try {
            return input.next();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return "";
        }
    }

    Boolean validLibraryNumber(String libraryNumber) {
        //https://stackoverflow.com/questions/18259644/how-to-check-if-a-string-matches-a-specific-format
        if (libraryNumber.matches("\\d{3}-\\d{4}")) {
            return true;
        }
        else {
            System.out.println("That is not a valid library number");
            return false;
        }
    }

    User findUserByLibraryNumber(String libraryNumber) {
        for (User user : users) {
            if (user.getLibraryNumber().equals(libraryNumber)) {
                return user;
            }
        }
        return null;
    }

    static Boolean validPassword(User user, String password) {
        if (user.getPassword().equals(password)) {
            user.toString();
            return true;
        } else {
            System.out.println("Invalid Password");
            return false;
        }
    }
}
