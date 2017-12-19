package com.test.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test_000_start {

    @Test
    public void startWebDriver(){
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://google.com");
        String str = chrome.getTitle();
        chrome.close();
        Assert.assertTrue(true);
        System.err.println(str);
    }
}
