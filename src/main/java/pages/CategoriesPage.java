package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CategoriesPage extends BasePage {

    public CategoriesPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By errorMessageLocator = By.cssSelector(".alert.alert-danger");
    private By categoriesMenu = By.xpath("//A[@_ngcontent-ng-c887457356=''][text()=' Categories ']");
    private By productItems = By.xpath("//a[@class='card' and @data-test]");
    private By drillCheckbox = By.cssSelector("input[type='checkbox'][value='Drill']");

    private WebDriverWait wait;



    public void navigateToCategory(String categoryName) {
        getElement(categoriesMenu).click();
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(categoryName)));
        categoryLink.click();
    }

    public int getNumberOfProducts() {
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productItems));
        return products.size();
    }


    public void filterByCheckbox(String checkboxText) {
        By checkboxLocator = By.xpath("//label[contains(text(),'" + checkboxText + "')]/input[@type='checkbox']");
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));
        checkbox.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productItems));
    }


}
