package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.ProductsPage;
import page_objects.ShoppingCartPage;

public class AddProducts extends HelperClass {
    ProductsPage productsPage = new ProductsPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    @Test
    public void addProductsToCart() {
        String shoppingCartHeader = productsPage.addProducts(driver);
        Assert.assertEquals(shoppingCartHeader, "Shopping Cart");
        String shippingAddressHeader = shoppingCartPage.verifyProductsDetailsInShoppingCart(driver);
        Assert.assertEquals(shippingAddressHeader, "Shipping Address");
    }
}
