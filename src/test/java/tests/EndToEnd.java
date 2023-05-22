package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.*;

public class EndToEnd extends HelperClass {
    HomePage homePage = new HomePage();
    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    ProductsPage productsPage = new ProductsPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    ShippingPage shippingPage = new ShippingPage();
    PaymentPage paymentPage = new PaymentPage();
    MyOrdersPage myOrdersPage = new MyOrdersPage();

    @Test
    public void endToEnd() {
        homePage.clickCreateAnAccountLink(driver);
        createAnAccountPage.fillingOutTheRegistrationForm(driver);
        productsPage.addProducts(driver);
        String shippingAddressHeader = shoppingCartPage.verifyProductsDetailsInShoppingCart(driver);
        Assert.assertEquals(shippingAddressHeader, "Shipping Address");
        String paymentHeader = shippingPage.fillingOutTheShippingForm(driver);
        Assert.assertEquals(paymentHeader, "Payment Method");
        String successOrderTitle = paymentPage.reviewAndPlaceOrder(driver);
        Assert.assertEquals(successOrderTitle, "Thank you for your purchase!");
        myOrdersPage.checkOrder(driver);
    }
}
