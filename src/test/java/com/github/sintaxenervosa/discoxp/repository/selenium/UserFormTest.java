package com.github.sintaxenervosa.discoxp.repository.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserFormTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
    }

    @AfterEach
    void tearDown() {
        if (!driver.toString().contains("(null)")) {
            driver.quit();
        }
    }

    @Test
    void formularioDeCadastro() {
        driver.get("http://localhost:5173/admin/register");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
                .sendKeys("teste@email.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")))
                .sendKeys("João Silva");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cpf")))
                .sendKeys("12345678901");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("group")))
                .sendKeys("ADMIN");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")))
                .sendKeys("Senha@123");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword")))
                .sendKeys("Senha@123");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm"))).click();
    }
}
