package com.twu.biblioteca;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserListTest {
    private static ByteArrayOutputStream byteArrayOutputStream;

    private static UserList userList;

    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000"));

        userList = new UserList(users);
    }


    @Test
    public void testFindUserByLibraryNumber() {
        User user = userList.findUserByLibraryNumber("123-4567");
        assertThat(user.getName(), is(equalTo("John Doe")));
    }

    @Test
    public void testValidLibraryNumber() {
        Boolean libraryNumberCheck = userList.validLibraryNumber("123-4567");
        assertThat(libraryNumberCheck, is(equalTo(true)));
    }

    @Test
    public void testInvalidLibraryNumber() {
        Boolean libraryNumberCheck = userList.validLibraryNumber("1234567");
        assertThat(libraryNumberCheck, is(equalTo(false)));
    }

    @Test
    public void testInvalidLibraryNumberWithChar() {
        Boolean libraryNumberCheck = userList.validLibraryNumber("qwerty");
        assertThat(libraryNumberCheck, is(equalTo(false)));
    }

    @Test
    public void testCheckPassword() {
        User user = new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000");
        Boolean passwordCheck = UserList.validPassword(user, "0000");
        assertThat(passwordCheck, is(equalTo(true)));
    }

    @Test
    public void testInvalidPassword() {
        User user = new User("123-4567", "John Doe", "jd@gmail.com", "12345678", "0000");
        Boolean passwordCheck = UserList.validPassword(user, "2222");
        assertThat(passwordCheck, is(equalTo(false)));
    }
}
