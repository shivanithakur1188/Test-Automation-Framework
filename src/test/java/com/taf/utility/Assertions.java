package com.taf.utility;

import org.junit.Assert;

public class Assertions {

    public static void assertTrue(boolean conditon, String message) {
        Assert.assertTrue(message, conditon);
    }

}
