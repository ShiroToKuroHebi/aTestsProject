package com.test.webdriver;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class test_000_start {

    private final WebDriver chrome = new ChromeDriver();

    @Test
    public void goToStand(){
        chrome.get("http://stand.vtb.jtcc.ru:16006/");
        assertEquals("VTB DBO front", chrome.getTitle());
    }

    @Test
    public void logIn(){
        WebElement loginField = chrome.findElement(By.xpath("//input[@placeholder='Логин']"));
        WebElement passwordField = chrome.findElement(By.xpath("//input[@type='password']"));
        WebElement buttonLogIn = chrome.findElement(By.xpath("//button[text()='Войти']"));

        loginField.clear();
        loginField.sendKeys("1111111111");
        passwordField.clear();
        passwordField.sendKeys("1111111111");
        buttonLogIn.click();
    }

    @Test
    public void createRP(){
        WebElement buttonCreate = chrome.findElement(By.xpath(".//*[text()='Создать ПП']/.."));

        buttonCreate.click();
    }

    @Test
    public void allInOne(){
        goToStand();
        logIn();
        createRP();
        //chrome.quit();
    }
}
