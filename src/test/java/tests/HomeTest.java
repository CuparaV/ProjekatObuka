package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class HomeTest extends BaseTest{

      private HomePage homePage;
    @BeforeMethod
    public void setUpHome(){
        homePage = new HomePage(driver);
    }


    @Test(description = "KORPA - 2.Verifikovati da broj proizvoda u korpi odgovara broju u crvenom balonu na ikonici " +
            "korpe")
    public void veifyProducts()  {
        homePage.addProductsInCart();
        homePage.verifyCartItemCountMatchesBalloon();
    }


}
