package com.twu.biblioteca;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BibliotecaAppTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static ByteArrayInputStream byteArrayInputStream;
    private static BibliotecaApp biblioteca = new BibliotecaApp();


    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

    }

    @Test
    public void testWelcomeMessagePrinted() {
        biblioteca.printWelcomeMessage();
        assertThat(byteArrayOutputStream.toString(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!") );
    }

    @Test
    public void testAlphaCharForInput() {
        int returnValue = biblioteca.getSelectedMenuOption(new Scanner("asdwfe"));
        assertThat(returnValue, is(equalTo(-1)));
    }

    @Test
    public void testNumbersForInput() {
        int returnValue = biblioteca.getSelectedMenuOption(new Scanner("10"));
        assertThat(returnValue, is(equalTo(10)));
    }


}

