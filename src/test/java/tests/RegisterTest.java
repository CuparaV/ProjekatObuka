package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
//@Listeners(TestListener.class)
public class RegisterTest extends BaseTest{

    private RegisterPage registerPage;
    private HomePage homePage;
    private LoginPage loginPage;


    @BeforeMethod
    public void setUpRegister(){
        registerPage = new RegisterPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }


    @Test(description = "Register user happy path")
    public void registerUserTest() throws InterruptedException {
        registerPage.registerUser();

    }


}
