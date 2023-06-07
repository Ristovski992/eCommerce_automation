package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyOrdersPage {
    FileReader fileReader = new FileReader();
    CustomMethods customMethods = new CustomMethods();

    public String orderNumberLink = "//*[@id='maincontent']/div[3]/div/div[2]/p[1]/a";
    public String orderNumberText = "//*[@id='maincontent']/div[3]/div/div[2]/p[1]/a/strong";
    public String orderNumberLabel = "//*[@id='maincontent']/div[2]/div[1]/div[1]/h1/span";
    public String orderStatusLabel = "//*[@id='maincontent']/div[2]/div[1]/div[1]/span";
    public String orderStatusValue = "PENDING";
    public String orderDateLabel = "//*[@id='maincontent']/div[2]/div[1]/div[1]/div[1]/span[2]";
    public String firstProductNameLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[1]/tr/td[1]/strong";
    public String firstProductSizeLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[1]/tr/td[1]/dl/dd[1]";
    public String firstProductColorLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[1]/tr/td[1]/dl/dd[2]";
    public String firstProductPriceLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[1]/tr/td[3]/span/span/span";
    public String firstProductQtyLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[1]/tr/td[4]/ul/li/span[2]";
    public String secondProductNameLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[2]/tr/td[1]/strong";
    public String secondProductSizeLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[2]/tr/td[1]/dl/dd[1]";
    public String secondProductColorLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[2]/tr/td[1]/dl/dd[2]";
    public String secondProductPriceLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[2]/tr/td[3]/span/span/span";
    public String secondProductQtyLabel = "/html/body/div[1]/main/div[2]/div[1]/div[2]/div[2]/table/tbody[2]/tr/td[4]/ul/li/span[2]";
    public String subTotalPriceLabel = "//*[@id='my-orders-table']/tfoot/tr[1]/td/span";
    public String shippingPriceLabel = "//*[@id='my-orders-table']/tfoot/tr[2]/td/span";
    public String grandTotalPriceLabel = "//*[@id='my-orders-table']/tfoot/tr[3]/td/strong/span";
    public String shippingAddressDetailsLabel = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div[1]/div/address";
    public String shippingMethodLabel = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div[2]/div";
    public String billingAddressDetailsLabel = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div[3]/div/address";
    public String paymentMethodLabel = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div[4]/div/dl/dt";
    public String paymentMethodValue = "Check / Money order";

    SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
    public String todayDate = formatter.format(new Date());

    public void checkOrder(WebDriver driver) {
        String orderNumber = driver.findElement(By.xpath(orderNumberText)).getText();
        driver.findElement(By.xpath(orderNumberLink)).click();
        Assert.assertEquals(driver.findElement(By.xpath(orderNumberLabel)).getText(), "Order # " + orderNumber);
        Assert.assertEquals(driver.findElement(By.xpath(orderStatusLabel)).getText(), orderStatusValue);
        // After 00:00 the date don't change on the website because of different time zone
        Assert.assertEquals(driver.findElement(By.xpath(orderDateLabel)).getText(), todayDate);
        List<String> JacketDataList = fileReader.ReadFile("JacketData.txt");
        Assert.assertEquals(driver.findElement(By.xpath(firstProductNameLabel)).getText(), JacketDataList.get(0));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductSizeLabel)).getText(), JacketDataList.get(2));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductColorLabel)).getText(), JacketDataList.get(3));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductPriceLabel)).getText(), JacketDataList.get(1));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductQtyLabel)).getText(), JacketDataList.get(4));
        List<String> PantsDataList = fileReader.ReadFile("PantsData.txt");
        Assert.assertEquals(driver.findElement(By.xpath(secondProductNameLabel)).getText(), PantsDataList.get(0));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductSizeLabel)).getText(), PantsDataList.get(2));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductColorLabel)).getText(), PantsDataList.get(3));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductPriceLabel)).getText(), PantsDataList.get(1));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductQtyLabel)).getText(), PantsDataList.get(4));
        List<String> ShippingAndTaxList = fileReader.ReadFile("ShippingAndTax.txt");
        float subTotalPrice = customMethods.stringToFloat(driver, subTotalPriceLabel);
        String subTotalPriceFromList = ShippingAndTaxList.get(2);
        float subTotalPriceFromListConverted = Float.parseFloat(subTotalPriceFromList);
        Assert.assertEquals(subTotalPrice, subTotalPriceFromListConverted);
        float shippingPrice = customMethods.stringToFloat(driver, shippingPriceLabel);
        String shippingPriceFromList = ShippingAndTaxList.get(3);
        float shippingPriceFromListConverted = Float.parseFloat(shippingPriceFromList);
        Assert.assertEquals(shippingPrice, shippingPriceFromListConverted);
        float grandTotalPrice = customMethods.stringToFloat(driver, grandTotalPriceLabel);
        String totalPriceFromList = ShippingAndTaxList.get(4);
        float totalPriceFromListConverted = Float.parseFloat(totalPriceFromList);
        Assert.assertEquals(grandTotalPrice, totalPriceFromListConverted);
        List<String> RegisterDataList = fileReader.ReadFile("RegisterData.txt");
        List<String> ShippingFormValuesList = fileReader.ReadFile("ShippingFormValues.txt");
        String firstName = RegisterDataList.get(0);
        String lastName = RegisterDataList.get(1);
        String company = ShippingFormValuesList.get(0);
        String address = ShippingFormValuesList.get(1);
        String city = ShippingFormValuesList.get(2);
        String region = ShippingAndTaxList.get(0);
        String tax = ShippingAndTaxList.get(1);
        String country = ShippingAndTaxList.get(0);
        String shippingMethodValue = ShippingFormValuesList.get(4);
        String phoneNumber = ShippingFormValuesList.get(3);
        String shipToCity = ShippingFormValuesList.get(2);
        String shippingAddressDetails = driver.findElement(By.xpath(shippingAddressDetailsLabel)).getText();
        Assert.assertEquals(shippingAddressDetails, firstName + " " + lastName + "\n" + company + "\n"
                + address + "\n" + shipToCity + ", " + region + ", "
                + tax + "\n" + country + "\n" + "T: " + phoneNumber);
        String shippingMethod = driver.findElement(By.xpath(shippingMethodLabel)).getText();
        Assert.assertEquals(shippingMethod, "Flat Rate - " + shippingMethodValue);
        String billingAddressDetails = driver.findElement(By.xpath(billingAddressDetailsLabel)).getText();
        Assert.assertEquals(billingAddressDetails, firstName + " " + lastName + "\n" + company + "\n"
                + address + "\n" + city + ", " + region + ", "
                + tax + "\n" + country + "\n" + "T: " + phoneNumber);
        String paymentMethod = driver.findElement(By.xpath(paymentMethodLabel)).getText();
        Assert.assertEquals(paymentMethod, paymentMethodValue);
    }
}
