package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector(".btnSubmit");
    private By signIn = By.cssSelector("[data-test='nav-sign-in']");
    private By errorMessageLocator = By.cssSelector(".alert.alert-danger");



    private  WebDriverWait wait;

    public LoginPage (WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
    }

    public void login (String username, String password) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void proceedToCheckoutIfLoggedIn() {
        By loggedInMessageLocator = By.xpath("//p[contains(text(), 'you are already logged in')]");
        By proceedToCheckoutButtonLocator = By.cssSelector("[data-test='proceed-2']");

        try {
            WebElement loggedInMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInMessageLocator));
            if (loggedInMessage.isDisplayed()) {
                System.out.println("User is already logged in. Proceeding to checkout.");
                WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButtonLocator));
                proceedButton.click();
            }
        } catch (TimeoutException e) {
            System.out.println("User is not logged in or message is not displayed. Proceeding as usual.");
        }

    }
    public void completeLoginAndProceed(String username, String password) {
        login(username, password);
        proceedToCheckoutIfLoggedIn();
    }
     public void signIn(){
         getElement(signIn).click();
     }

    public void loginWithDifferentCredentials(String email, String password) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public String getErrorMessage() {
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMsg.getText();
    }

    }

