package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.Log4jManager;

@Listeners(tests.Listeners.class)
public class TC01_Basket extends BaseTest{

    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;
    BasketPage basketPage;
    BasePage basePage;


    @Test (priority = 0)
    public void checkBasketDiscount() throws InterruptedException {
        homePage = new HomePage(driver, wait, sa);
        loginPage = new LoginPage(driver, wait, sa);
        productsPage = new ProductsPage(driver, wait, sa);
        basketPage = new BasketPage(driver, wait, sa);
        basePage = new BasePage(driver,wait,sa);
        homePage.goToLcWaikiki();
        homePage.clickSignIn();
        loginPage.oneWayLogin("tahafurkan994@gmail.com", "tahaf123");
        homePage.writeAndSearch("pantolan","\"pantolan\"");
        homePage.scrollEndOfPage();
        homePage.clickMoreProducts();
        homePage.clickRandomProduct();
        productsPage.clickAddToCart();
        String productDiscount = productsPage.getPrice();
        productsPage.clickToGoBasket();
        basketPage.assertDicounts(productDiscount);
        basketPage.assertBasketCount();
        basketPage.clickDeleteProduct();
        basketPage.assertEmptyBasket("Sepetinizde ürün bulunmamaktadır.");
        Thread.sleep(3000);
        basePage.softAssertAll();
    }
}
