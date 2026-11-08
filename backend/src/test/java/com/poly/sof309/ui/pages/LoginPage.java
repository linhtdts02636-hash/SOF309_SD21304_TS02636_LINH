package com.poly.sof309.ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * =========================================================================
 * [Y4] PAGE OBJECT MODEL (POM) CHO TRANG ĐĂNG NHẬP CỦA DEVCLIMB
 * Class này chỉ quản lý các phần tử (Locator) và hành động trên trang Web.
 * Trang đăng nhập: frontend/pages/guest/auth/login.html
 * =========================================================================
 */
public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // 1. Định nghĩa các Locator trên trang đăng nhập của DevClimb
    private final By txtEmail = By.id("email");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.cssSelector("#loginForm button[type='submit']");
    private final By lblErrorMessage = By.id("loginError");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 2. Các hành động nghiệp vụ
    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword));
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorMessage)).getText();
    }
}
