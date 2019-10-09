package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MenuTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static Menu menu;

    @Before
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("Opt 1 ");
        menuOptions.add("Opt 2 ");

        menu = new Menu(menuOptions);
    }

    @Test
    public void testMenuOptionsPrinted(){
        menu.printMenuOptions();
        assertThat(byteArrayOutputStream.toString(), containsString("Opt 1"));
    }


    @Test
    public void testNegativeNumbersForMenuOption() {
        menu.parseInput(-1);
        assertThat(byteArrayOutputStream.toString(), containsString("Please select a valid option:"));
    }
}
