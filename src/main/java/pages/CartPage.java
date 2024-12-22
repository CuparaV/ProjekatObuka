package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {
    private By productPrices = By.cssSelector("[data-test='product-price']");
    private By productQuantities = By.cssSelector("[data-test='product-quantity']");
    private By productLinePrices = By.cssSelector("[data-test='line-price']");
    private By totalPrice = By.cssSelector("[data-test='cart-total']");

    private By cartItems = By.cssSelector(".cart-item");
    private By productNameLocator = By.cssSelector(".product-name");

    private By checkoutButton = By.cssSelector(".btn.btn-success");
    private By checkoutButtonBillingAddress = By.xpath("//BUTTON[@_ngcontent-ng-c4069156187=''][text()='Proceed to checkout ']");


    private By shippingAddressField = By.id("address");
    private By shippingCityField = By.id("city");
    private By shippingStateField = By.id("state");
    private By shippingCountry = By.id("country");
    private By shippingPostCode = By.id("postcode");

    private By confirmButton = By.xpath("//BUTTON[@_ngcontent-ng-c3905028015=''][text()=' Confirm ']");
    private By successMessage = By.cssSelector(".alert.alert-success.ng-star-inserted");




    private WebDriverWait wait;


    public CartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyTotalPrice() {

        List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices));
        List<WebElement> quantities = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productQuantities));
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));

        double expectedTotal = 0.0;


        for (int i = 0; i < prices.size(); i++) {
            double price = parsePrice(prices.get(i).getText());
            int quantity = Integer.parseInt(quantities.get(i).getAttribute("value"));

            expectedTotal += price * quantity;
        }

        double displayedTotal = parsePrice(total.getText());

        System.out.println("Expected Total price: " + expectedTotal + "$");
        System.out.println("Displayed Total price: " + displayedTotal + "$");

        Assert.assertEquals(expectedTotal, displayedTotal, "Total price does not match!");
    }


    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }


    public void clickCheckoutButton() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutBtn.click();
    }

    public void clickCheckoutButtonOnBillingAddress() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonBillingAddress));
        checkoutBtn.click();
    }


    public void verifyPurchaseSuccessColorAndMessage() {

        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

        String actualText = successMsg.getText();
        String expectedText = "Payment was successful";
        Assert.assertEquals(actualText, expectedText, "The success message text is incorrect!");

        String actualColor = successMsg.getCssValue("background-color");
        String expectedColor = "rgba(209, 231, 221, 1)";
        Assert.assertEquals(actualColor, expectedColor, "The success message background color is incorrect!");
    }


    public void fillShippingInformation(String address, String city, String state, String country, String postCode
    ) {
        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingAddressField));
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingCityField));
        WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingStateField));
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingCountry));
        WebElement postCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingPostCode));

        addressField.sendKeys(address);
        cityField.sendKeys(city);
        stateField.sendKeys(state);
        countryField.sendKeys(country);
        postCodeField.sendKeys(postCode);
    }


    public void completePurchase(String address, String city, String state, String country, String postcode) {
        fillShippingInformation(address, city, state, country, postcode);
        clickCheckoutButtonOnBillingAddress();
        selectPaymentMethod("Cash on Delivery");
        getElementWithWait(confirmButton).click();
        verifyPurchaseSuccessColorAndMessage();
    }


    public void selectPaymentMethod(String paymentMethod) {
        WebElement paymentDropdown = driver.findElement(By.id("payment-method"));
        Select select = new Select(paymentDropdown);
        select.selectByVisibleText(paymentMethod);
    }


}
