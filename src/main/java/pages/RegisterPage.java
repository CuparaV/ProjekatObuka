package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Constants;

public class RegisterPage extends BasePage{


    private By registerLink = By.xpath("//A[@_ngcontent-ng-c1371883730=''][text()='Register your account']");

    private By firstNameField = By.xpath("//*[@for='first_name']/following-sibling::input");
    private By lastNameField = By.xpath("//*[@for='last_name']/following-sibling::input");
    private By dob = By.id("dob");
    private By address = By.id("address");
    private By postCode = By.id("postcode");
    private By city = By.id("city");
     private By state = By.id("state");
     private By country = By.id("country");
     private By phone = By.id("phone");

    private By emailNameField = By.id("email");
    private By passwordField = By.id("password");

    private By registerButton = By.cssSelector("[type='submit']");

    private By welcomePageText = By.cssSelector(".page-body .result");

    private By signIn = By.cssSelector("[data-test='nav-sign-in']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }


    public void registerUser() throws InterruptedException {
        getElementWithWait(signIn).click();
        Thread.sleep(500);
        getElementWithWait(registerLink).click();
        Thread.sleep(500);
        typeIn(firstNameField, Constants.FIRST_NAME_FIELD);
        typeIn(lastNameField, Constants.LAST_NAME_FIELD);
        typeIn(dob, Constants.DOB);
        typeIn(address, Constants.ADDRESS);
        typeIn(postCode, Constants.POSTCODE);
        typeIn(city, Constants.CITY);
        typeIn(state, Constants.STATE);
        selectCountry();
        typeIn(phone, Constants.PHONE);
        typeIn(emailNameField, Constants.EMAIL_NAME_FIELD);
        typeIn(passwordField, Constants.PASSWORD_NAME_FIELD);
        getElementWithWait(registerButton).click();
    }


    private void selectCountry(){
        WebElement  element = getElement(country);
        Select select = new Select(element);
        select.selectByValue(Constants.COUNTRY);
    }


}
