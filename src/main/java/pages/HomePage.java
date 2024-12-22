package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;


public class HomePage extends BasePage {

    private By  hammerProduct = By.xpath("//h5[contains(normalize-space(text()), 'Combination Pliers')]");
    private By  thorHammerProduct = By.xpath("//h5[contains(normalize-space(text()), 'Bolt Cutters')]");
    private By  addToCartButton = By.xpath("//*[@id='btn-add-to-cart']");
    private By homeMenuButton = By.cssSelector(".nav-link.active");

    private By getHomeMenuButton = By.cssSelector(".nav-link.active");

    private By signIn = By.cssSelector("[data-test='nav-sign-in']");

    private By numberOfProductsInCart = By.id(".badge.rounded-pill.bg-danger");
    private By baloon = By.xpath("//span[@id='lblCartCount']");


    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void addProductsInCart()  {
        getElement(hammerProduct).click();
        waitForElementToBeClickable(addToCartButton).click();
        waitForBalloonCountToUpdate(1);
        waitForElementToBeClickable(addToCartButton).click();
        waitForBalloonCountToUpdate(2);
        waitForElementToBeClickable(homeMenuButton).click();
        getElement(thorHammerProduct).click();
        waitForElementToBeClickable(addToCartButton).click();
        waitForBalloonCountToUpdate(3);
        WebElement baloonElement = waitForElementToBeClickable(By.xpath("//span[@id='lblCartCount']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", baloonElement);

        WebElement balloonElement = waitForElementToBeClickable(By.xpath("//span[@id='lblCartCount']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", balloonElement);

    }


    private void waitForBalloonCountToUpdate(int expectedCount) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(driver -> {
                    WebElement balloon = driver.findElement(By.cssSelector(".badge.rounded-pill.bg-danger"));
                    String countText = balloon.getText().trim();
                    return countText.equals(String.valueOf(expectedCount));
                });
    }


    public boolean verifyCartItemCountMatchesBalloon() {

        WebElement balloon = waitForElementToBeVisible(By.cssSelector(".badge.rounded-pill.bg-danger"));
        int balloonCount = Integer.parseInt(balloon.getText().trim());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[data-test='product-quantity']")));

        List<WebElement> cartRows = driver.findElements(By.cssSelector("[data-test='product-quantity']"));

        int totalQuantity = 0;
        for (WebElement row : cartRows) {
            int quantity = Integer.parseInt(row.getAttribute("value").trim()); // Assuming 'value' contains the quantity
            totalQuantity += quantity;
        }

        System.out.println("Balloon Count: " + balloonCount);
        System.out.println("Total Quantity in Cart Rows: " + totalQuantity);

        return balloonCount == totalQuantity;
    }

    public void signInOnPage() {
         getElementWithWait(signIn).click();
         getElementWithWait(getHomeMenuButton).click();
    }


}










