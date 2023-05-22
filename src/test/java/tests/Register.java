package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.CreateAnAccountPage;
import page_objects.HomePage;

public class Register extends HelperClass {
    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();
    HomePage homePage = new HomePage();

    @Test
    public void shouldRegister() {
        String createNewCustomerHeader = homePage.clickCreateAnAccountLink(driver);
        Assert.assertEquals(createNewCustomerHeader, "Create New Customer Account");
        String successRegistrationMessage = createAnAccountPage.fillingOutTheRegistrationForm(driver);
        Assert.assertEquals(successRegistrationMessage, "Thank you for registering with Main Website Store.");
    }
}