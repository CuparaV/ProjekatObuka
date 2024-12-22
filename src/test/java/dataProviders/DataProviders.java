package dataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "invalidCredentials")
    public Object[][] provideInvalidCredentials() {
        return new Object[][]{
                {"", "", "Email is required"},              // Both fields empty
                {"mail.mail.com", "", "Email format is invalid"},           // Invalid email format
                {"ecsd16@gmail.com", "", "Password is required"},       // Password missing
                {"", "TEst0125!", "Email is required"},                // Email missing
                {"ecsd16@gmail.com", "wrongpass", "Invalid email or password"} // Valid email but wrong password
        };
    }
}
