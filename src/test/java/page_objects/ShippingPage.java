package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ShippingPage {
    CustomMethods customMethods = new CustomMethods();
    FileReader fileReader = new FileReader();

    public String companyField = "//*[@id='shipping-new-address-form']/div[3]/div/input";
    public String companyValue = customMethods.generateRandomStringWithoutNumbers(6);
    public String streetAddressField = "//*[@id='shipping-new-address-form']/fieldset/div/div[1]/div/input";
    public String streetAddressValue = customMethods.generateRandomStringWithoutNumbers(10);
    public String cityField = "//*[@id='shipping-new-address-form']/div[4]/div/input";
    public String cityValue = "Tetovo";
    public String phoneNumberField = "//*[@id='shipping-new-address-form']/div[9]/div/input";
    public long phoneNumberValue = ThreadLocalRandom.current().nextLong(100000000L, 899999999L);
    public String shippingMethodsPriceLabel = "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[2]/span/span";
    public String shippingMethodsFlatRateLabel = "//*[@id='label_method_flatrate_flatrate']";
    public String itemsInCartField = "//*[@id='opc-sidebar']/div[1]/div/div[1]/strong/span[1]";
    public String orderSummaryDropDown = "//*[@id='opc-sidebar']/div[1]/div/div[1]";
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
    public String nextBtn = "//*[@id='shipping-method-buttons-container']/div/button";
    public String paymentHeaderElement = "//*[@id='checkout-payment-method-load']/div/div/div[1]";


    public String fillingOutTheShippingForm(WebDriver driver) {
        driver.findElement(By.xpath(companyField)).sendKeys(companyValue);
        driver.findElement(By.xpath(streetAddressField)).sendKeys(streetAddressValue);
        driver.findElement(By.xpath(cityField)).sendKeys(cityValue);
        driver.findElement(By.xpath(phoneNumberField)).sendKeys(String.valueOf(phoneNumberValue));
        Assert.assertEquals(driver.findElement(By.xpath(itemsInCartField)).getText(), "2");
        customMethods.waitUntilXpathElementToBeClickable(driver, orderSummaryDropDown);
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
        List<String> ShippingAndTaxList = fileReader.ReadFile("ShippingAndTax.txt");
        float shippingPrice = customMethods.stringToFloat(driver, shippingMethodsPriceLabel);
        String shippingPriceFromList = ShippingAndTaxList.get(3);
        float shippingPriceFromListConverted = Float.parseFloat(shippingPriceFromList);
        Assert.assertEquals(shippingPrice, shippingPriceFromListConverted);
        String shippingFlatRate = driver.findElement(By.xpath(shippingMethodsFlatRateLabel)).getText();
        fileReader.CreateFile("ShippingFormValues.txt");
        fileReader.WriteToFile("ShippingFormValues.txt", companyValue + "\n" + streetAddressValue + "\n" + cityValue + "\n" + phoneNumberValue + "\n" + shippingFlatRate);
        customMethods.waitUntilVisibilityOfXpathElement(driver, nextBtn);
        driver.findElement(By.xpath(nextBtn)).click();
        String paymentHeader = driver.findElement(By.xpath(paymentHeaderElement)).getText();
        return paymentHeader;
    }
}
