package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CartTest extends BaseTest {

    private CartPage cartPage = new CartPage(driver);
    private WishlistPage wishlistPage = new WishlistPage(driver);
    private LoginPage loginPage = new LoginPage(driver);
    private RegisterPage registerPage = new RegisterPage(driver);

    @BeforeMethod
    public void setCartPage() {
        cartPage = new CartPage(driver);
        wishlistPage = new WishlistPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }


    @Test (description =
            "KORPA " +
                    "1. Dodati proizvoljno dva proizvoda u korpu. Kolicina jednog od proizvoda treba da bude minimum " +
                    "2\n" +
            "3. Verifikovati da se Total cena poklapa sa cena * kolicina za sve proizvode u korpi")
    public void testCartTotalPrice() {
        CartPage cartPage = new CartPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.addProductsInCart();
        cartPage.verifyTotalPrice();
    }

    @Test ( description = "Wish-lista\n" +
            "1. Dodati proizvod u wish-listu\n" +
            "2. Verifikovati u profilu korisnika na stranici Favorites da je proizvod dodat. Verifikaciju izvrsiti po imenu proizvoda")
    public void addProductToWishlistAndVerify() throws InterruptedException {
        registerPage.registerUser();
        wishlistPage.addProductToWishlist();
        Thread.sleep(1000);
        boolean isInWishlist = wishlistPage.isProductInWishlist("Combination Pliers");
        Assert.assertTrue(isInWishlist,
                "The product 'Combination Pliers' should be in the wishlist ");

    }

}



