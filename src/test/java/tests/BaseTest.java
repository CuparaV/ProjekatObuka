package tests;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Constants;

public class BaseTest {


    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initialSetup(){
        driver = DriverManager.setDriver("chrome");
        driver.get(Constants.URL);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        if (driver != null){
            driver.quit();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }

}
