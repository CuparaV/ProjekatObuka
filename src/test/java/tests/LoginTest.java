package tests;

import dataProviders.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest{

    private LoginPage loginPage;
    private CartPage cartPage;
    private HomePage homePage;

    @BeforeMethod
    public void setLoginPage(){
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void signInToPage() {
                homePage.signInOnPage();
                    }

    @Test (description = "4. Zavrsiti kupovinu proizvoda, platiti proizvode i verifikovati uspesnost kupovine")
    public void testLoginAndCompletePurchase()  {
        homePage.signInOnPage();
        homePage.addProductsInCart();
        cartPage.clickCheckoutButton();

        loginPage.login("ecsd16@gmail.com", "TEst0125!");
        loginPage.proceedToCheckoutIfLoggedIn();

        cartPage.completePurchase("Marka Markovica", "Beograd", "Srbija", "RS", "11000");



    }

//     #Login
//1. Uraditi negativne testove za login. Jedan test treba da procita sve kredencijale preko dataProvidera

    @Test(dataProvider = "invalidCredentials", dataProviderClass = DataProviders.class)
    public void testInvalidLogin(String email, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn();

        loginPage.loginWithDifferentCredentials(email, password);

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");
    }
}


