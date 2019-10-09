package com.twu.biblioteca;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private static ByteArrayOutputStream byteArrayOutputStream;
    private static BibliotecaApp biblioteca;


    @BeforeClass
    public static void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        biblioteca = new BibliotecaApp();
    }


}

