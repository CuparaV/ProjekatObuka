package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoriesPage;

public class CategoriesTest extends BaseTest {

    private CategoriesPage categoriesPage;

    @BeforeMethod
    public void setUpCategories() {
        categoriesPage = new CategoriesPage(driver);
    }

    @Test ( description = "#Kategorije\n" +
            "1. Iz header-menu izabrati kategoriju Power Tools\n" +
            "2. Odabrati check-box Drill\n" +
            "3. Verifikovati da je broj proizvoda na stranici manji od broja proizvoda pre filtriranja")

    public void filterProductsInCategory() {
        categoriesPage.navigateToCategory("Power Tools");
        int initialProductCount = categoriesPage.getNumberOfProducts();
        System.out.println("Initial product count: " + initialProductCount);
        categoriesPage.filterByCheckbox("Drill");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int updatedProductCount = categoriesPage.getNumberOfProducts();
        System.out.println("Updated product count after filtering by Drill: " + updatedProductCount);


        Assert.assertTrue(updatedProductCount < initialProductCount, "Filtered product count should be less than the initial count");
        System.out.println("Test Passed: Filtered product count is less than the initial count.");
    }
}
