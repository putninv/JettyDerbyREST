package com.putnin.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Ignore;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for MoneyController.
 *
 * @author putnin.v@gmail.com
 */
public class MoneyControllerTest {
    private static int USER_ACCOUNT_ID = 981111;

    @Ignore
    @Test
    public void getAccountSum() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8085/money/checkAccountSum/" +
                USER_ACCOUNT_ID).openConnection();
        http.connect();
        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }
}
