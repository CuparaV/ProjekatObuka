package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;
import java.util.List;

public class WishlistPage extends BasePage {
    private WebDriverWait wait;
    LoginPage loginPage = new LoginPage(driver);
    CategoriesPage categoriesPage = new CategoriesPage(driver);

    private By addToWishlistButton = By.cssSelector("button.add-to-wishlist");
    private By productNameLocator = By.cssSelector("h5[data-test='product-name']");
    private By combinationPliers = By.xpath("//h5[contains(text(), 'Combination Pliers')]");
    private By wishListButton = By.id("btn-add-to-favorites");

    private By getHomeMenuButton = By.cssSelector(".nav-link.active");
    private By myAccountMenu = By.xpath("//A[@id='menu']");
    private By favoriteList = By.xpath("//A[@_ngcontent-ng-c887457356=''][text()='My favorites']");
    private By wishlistItems = By.cssSelector(".card.mb-3.ng-star-inserted");

    private By signIn = By.cssSelector("[data-test='nav-sign-in']");


    private By categoriesButtonDropDown = By.cssSelector(".nav-link.dropdown-toggle");
    private By handToolDropDown = By.xpath("//a[@_ngcontent-ng-c887457356 and contains(text(), 'Hand Tools')]");

    public WishlistPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
    }



    public void addProductToWishlist() throws InterruptedException {
        getElementWithWait(signIn).click();
        loginPage.login(Constants.USERNAME_EMAIL, Constants.PASSWORD);
        Thread.sleep(2000);
        getElementWithWait(categoriesButtonDropDown).click();
        getElementWithWait(handToolDropDown).click();
        getElementWithWait(combinationPliers).click();
        getElementWithWait(wishListButton).click();
        Thread.sleep(200);
        goToFavoritesPage();

    }


    public void goToFavoritesPage() {
        getElementWithWait(myAccountMenu).click();
        getElementWithWait(favoriteList).click();
    }


    public boolean isProductInWishlist(String productName) {

        List<WebElement> items = driver.findElements(wishlistItems);

        for (WebElement item : items) {
            String itemName = item.findElement(productNameLocator).getText().trim();
            if (itemName.equals(productName)) {
                return true;
            }
        }
        return false;
    }



}
