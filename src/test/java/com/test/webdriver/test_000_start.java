package com.test.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class test_000_start {

    private WebDriver chrome = new ChromeDriver();

    @Test
    public void goToStand() {
        chrome.get("http://stand.vtb.jtcc.ru:16006/");
        assertEquals("VTB DBO front", chrome.getTitle());
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void logIn() {
        WebElement loginField = chrome.findElement(By.xpath("//input[@placeholder='Логин']"));
        WebElement passwordField = chrome.findElement(By.xpath("//input[@type='password']"));
        WebElement buttonLogIn = chrome.findElement(By.xpath("//button[text()='Войти']"));

        loginField.clear();
        loginField.sendKeys(Data.clientLogin);
        passwordField.clear();
        passwordField.sendKeys(Data.clientPswrd);
        buttonLogIn.click();
    }

    @Test
    public void createRP() {
        WebElement buttonCreate = chrome.findElement(By.xpath(".//*[text()='Создать ПП']/.."));

        buttonCreate.click();
    }

    @Test
    public void makeItHappen() {
        goToStand();
        logIn();
        createRP();
        
        
        
        //chrome.quit();
    }
}
