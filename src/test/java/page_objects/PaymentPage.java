package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class PaymentPage {
    FileReader fileReader = new FileReader();
    CustomMethods customMethods = new CustomMethods();

    public String billingAddressDetailsLabel = "//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div[2]/div/div[2]";
    public String cartSubtotalPriceLabel = "//*[@id='opc-sidebar']/div[1]/table/tbody/tr[1]/td/span";
    public String shippingPriceLabel = "//*[@id='opc-sidebar']/div[1]/table/tbody/tr[2]/td/span";
    public String orderTotalPriceLabel = "//*[@id='opc-sidebar']/div[1]/table/tbody/tr[3]/td/strong/span";
    public String itemsInCartField = "//*[@id='opc-sidebar']/div[1]/div/div[1]/strong/span[1]";
    public String orderSummaryDropDown = "//*[@id='opc-sidebar']/div[1]/div";
    public String firstProductNameLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[1]/div/div/div[1]/div[1]/strong";
    public String firstProductQtyLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[1]/div/div/div[1]/div[1]/div/span[2]";
    public String firstProductPriceLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[1]/div/div/div[1]/div[2]/span/span/span";
    public String firstProductExpandDropDown = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[1]/div/div/div[2]/span";
    public String firstProductSizeLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[1]/div/div/div[2]/div/dl/dd[1]";
    public String firstProductColorLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[1]/div/div/div[2]/div/dl/dd[2]";
    public String secondProductNameLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[2]/div/div/div[1]/div[1]/strong";
    public String secondProductQtyLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[2]/div/div/div[1]/div[1]/div/span[2]";
    public String secondProductPriceLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[2]/div/div/div[1]/div[2]/span/span/span";
    public String secondProductExpandDropDown = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[2]/div/div/div[2]/span";
    public String secondProductSizeLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[2]/div/div/div[2]/div/dl/dd[1]";
    public String secondProductColorLabel = "//*[@id='opc-sidebar']/div[1]/div/div[2]/div/ol/li[2]/div/div/div[2]/div/dl/dd[2]";
    public String shipToDetailsLabel = "//*[@id='opc-sidebar']/div[2]/div/div[1]/div[2]";
    public String shippingMethodLabel = "//*[@id='opc-sidebar']/div[2]/div/div[2]/div[2]/span";
    public String placeOrderBtn = "//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div[4]/div/button";
    public String successOrderTitleElement = "//*[text()='Thank you for your purchase!']";


    public String reviewAndPlaceOrder(WebDriver driver) {
        List<String> RegisterDataList = fileReader.ReadFile("RegisterData.txt");
        List<String> ShippingFormValuesList = fileReader.ReadFile("ShippingFormValues.txt");
        List<String> ShippingAndTaxList = fileReader.ReadFile("ShippingAndTax.txt");
        String firstName = RegisterDataList.get(0);
        String lastName = RegisterDataList.get(1);
        String address = ShippingFormValuesList.get(1);
        String city = ShippingFormValuesList.get(2);
        String region = ShippingAndTaxList.get(0);
        String tax = ShippingAndTaxList.get(1);
        String country = ShippingAndTaxList.get(0);
        String phoneNumber = ShippingFormValuesList.get(3);
        String shipToCity = ShippingFormValuesList.get(2);
        String billingAddressDetails = driver.findElement(By.xpath(billingAddressDetailsLabel)).getText();
        Assert.assertEquals(billingAddressDetails, firstName + " " + lastName + "\n"
                + address + "\n" + city + ", " + region + " "
                + tax + "\n" + country + "\n" + phoneNumber);
        float cartSubTotalPrice = customMethods.stringToFloat(driver, cartSubtotalPriceLabel);
        String cartSubTotalPriceFromList = ShippingAndTaxList.get(2);
        float cartSubTotalPriceConverted = Float.parseFloat(cartSubTotalPriceFromList);
        Assert.assertEquals(cartSubTotalPrice, cartSubTotalPriceConverted);
        float shippingPrice = customMethods.stringToFloat(driver, shippingPriceLabel);
        String shippingPriceFromList = ShippingAndTaxList.get(3);
        float shippingPriceFromListConverted = Float.parseFloat(shippingPriceFromList);
        Assert.assertEquals(shippingPrice, shippingPriceFromListConverted);
        float orderTotalPrice = customMethods.stringToFloat(driver, orderTotalPriceLabel);
        String orderTotalPriceFromList = ShippingAndTaxList.get(4);
        float orderTotalPriceFromListConverted = Float.parseFloat(orderTotalPriceFromList);
        Assert.assertEquals(orderTotalPrice, orderTotalPriceFromListConverted);
        Assert.assertEquals(driver.findElement(By.xpath(itemsInCartField)).getText(), "2");
        driver.findElement(By.xpath(orderSummaryDropDown)).click();
        List<String> JacketDataList = fileReader.ReadFile("JacketData.txt");
        Assert.assertEquals(driver.findElement(By.xpath(firstProductNameLabel)).getText(), JacketDataList.get(0));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductQtyLabel)).getText(), JacketDataList.get(4));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductPriceLabel)).getText(), JacketDataList.get(1));
        driver.findElement(By.xpath(firstProductExpandDropDown)).click();
        Assert.assertEquals(driver.findElement(By.xpath(firstProductSizeLabel)).getText(), JacketDataList.get(2));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductColorLabel)).getText(), JacketDataList.get(3));
        List<String> PantsDataList = fileReader.ReadFile("PantsData.txt");
        Assert.assertEquals(driver.findElement(By.xpath(secondProductNameLabel)).getText(), PantsDataList.get(0));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductQtyLabel)).getText(), PantsDataList.get(4));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductPriceLabel)).getText(), PantsDataList.get(1));
        driver.findElement(By.xpath(secondProductExpandDropDown)).click();
        Assert.assertEquals(driver.findElement(By.xpath(secondProductSizeLabel)).getText(), PantsDataList.get(2));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductColorLabel)).getText(), PantsDataList.get(3));
        String shipToDetails = driver.findElement(By.xpath(shipToDetailsLabel)).getText();
        Assert.assertEquals(shipToDetails, firstName + " " + lastName + "\n"
                + address + "\n" + shipToCity + ", " + region + " "
                + tax + "\n" + country + "\n" + phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath(shippingMethodLabel)).getText(), "Flat Rate - " + ShippingFormValuesList.get(4));
        driver.findElement(By.xpath(placeOrderBtn)).click();
        String successOrderTitle = driver.findElement(By.xpath(successOrderTitleElement)).getText();
        return successOrderTitle;
    }
}
