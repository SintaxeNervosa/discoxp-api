package com.github.sintaxenervosa.discoxp.repository.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTestSelenium {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // -------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------

    private void abrirLogin() {
        driver.get("http://localhost:5173/");
    }

    private WebElement inputEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }

    private WebElement inputSenha() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
    }

    private WebElement botaoLogin() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
    }

    private WebElement toastErro() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".Toastify__toast--error")
        ));
    }

    private WebElement toastSucesso() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".Toastify__toast--info")
        ));
    }

    // -------------------------------------------------------------
    // TESTES
    // -------------------------------------------------------------

    @Test
    void deveFalhar_QuandoEmailNaoInformado() {
        abrirLogin();

        inputEmail().sendKeys("");
        inputSenha().sendKeys("@Teste1234");
        botaoLogin().click();

        String erro = toastErro().getText();
        Assertions.assertTrue(erro.contains("Email e/ou senha invalidos,"));
    }

    @Test
    void deveFalhar_QuandoSenhaNaoInformada() {
        abrirLogin();

        inputEmail().sendKeys("admin@admin.com");
        inputSenha().sendKeys("");
        botaoLogin().click();

        String erro = toastErro().getText();
        Assertions.assertTrue(erro.contains("Email e/ou senha invalidos,"));
    }

    @Test
    void deveFalhar_QuandoUsuarioNaoExiste() {
        abrirLogin();

        inputEmail().sendKeys("x@x.com");
        inputSenha().sendKeys("@Teste1234");
        botaoLogin().click();

        String erro = toastErro().getText();
        Assertions.assertTrue(erro.contains("Email e/ou senha inválidos"));
    }

    @Test
    void deveFalhar_QuandoSenhaIncorreta() {
        abrirLogin();

        inputEmail().sendKeys("admin@admin.com");
        inputSenha().sendKeys("Errada");
        botaoLogin().click();

        String erro = toastErro().getText();
        Assertions.assertTrue(erro.contains("Email e/ou senha invalidos,"));
    }

    @Test
    void deveLogar_QuandoCredenciaisCorretas() {
        abrirLogin();

        inputEmail().sendKeys("admin@admin.com");
        inputSenha().sendKeys("@Teste1234");
        botaoLogin().click();

        WebElement sucesso = toastSucesso();
        System.out.println(sucesso.getText());
        Assertions.assertTrue(sucesso.getText().contains("Login bem-sucedido!"));

        wait.until(ExpectedConditions.urlContains("/choice"));
        String urlAtual = driver.getCurrentUrl();

        Assertions.assertEquals("http://localhost:5173/choice", urlAtual);
    }
}
