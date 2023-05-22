package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ShoppingCartPage {
    FileReader fileReader = new FileReader();
    CustomMethods customMethods = new CustomMethods();

    public String firstProductNameLink = "//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[1]/div/strong/a";
    public String firstProductSizeLabel = "//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[1]/div/dl/dd";
    public String firstProductColorLabel = "//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[1]/div/dl/dd[2]";
    public String firstProductPriceLabel = "//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[2]/span/span/span";
    public String firstProductSubTotalLabel = "//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[4]/span/span/span";
    public String firstProductQtyField = "/html/body/div[1]/main/div[3]/div/div[2]/form/div[1]/table/tbody[1]/tr[1]/td[3]/div/div/label/input";
    public String secondProductNameLink = "//*[@id='shopping-cart-table']/tbody[2]/tr[1]/td[1]/div/strong/a";
    public String secondProductSizeLabel = "//*[@id='shopping-cart-table']/tbody[2]/tr[1]/td[1]/div/dl/dd";
    public String secondProductColorLabel = "//*[@id='shopping-cart-table']/tbody[2]/tr[1]/td[1]/div/dl/dd[2]";
    public String secondProductPriceLabel = "//*[@id='shopping-cart-table']/tbody[2]/tr[1]/td[2]/span/span/span";
    public String secondProductSubTotalLabel = "//*[@id='shopping-cart-table']/tbody[2]/tr[1]/td[4]/span/span/span";
    public String secondProductQtyField = "/html/body/div[1]/main/div[3]/div/div[2]/form/div[1]/table/tbody[2]/tr[1]/td[3]/div/div/label/input";
    public String proceedToCheckoutBtn = "//*[@id='maincontent']/div[3]/div/div[2]/div[1]/ul/li[1]/button";
    public String subTotalLabel = "//*[@id='cart-totals']/div/table/tbody/tr[1]/td/span";
    public String shippingPriceLabel = "//*[@id='cart-totals']/div/table/tbody/tr[2]/td/span";
    public String orderTotalLabel = "//*[@id='cart-totals']/div/table/tbody/tr[3]/td/strong/span";
    public String estimateShippingAndTaxDropDown = "//*[@id='block-shipping']/div[1]";
    public String countyField = "//*[@id='shipping-zip-form']/fieldset/div[1]/div/select";
    public String stateProvinceField = "//*[@id='shipping-zip-form']/fieldset/div[3]/div/input";
    public String stateProvinceValue = "Macedonia";
    public String zipPostalCodeField = "//*[@id='shipping-zip-form']/fieldset/div[4]/div/input";
    public String zipPostalCodeValue = "1200";
    public String shippingAddressHeaderElement = "//*[@id='shipping']/div[1]";

    public String verifyProductsDetailsInShoppingCart(WebDriver driver) {
        List<String> JacketDataList = fileReader.ReadFile("JacketData.txt");
        Assert.assertEquals(driver.findElement(By.xpath(firstProductNameLink)).getText(), JacketDataList.get(0));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductPriceLabel)).getText(), JacketDataList.get(1));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductSizeLabel)).getText(), JacketDataList.get(2));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductColorLabel)).getText(), JacketDataList.get(3));
        Assert.assertEquals(driver.findElement(By.xpath(firstProductSubTotalLabel)).getText(), "$60.00");
        Assert.assertEquals(driver.findElement(By.xpath(firstProductQtyField)).getAttribute("value"), JacketDataList.get(4));
        List<String> PantsDataList = fileReader.ReadFile("PantsData.txt");
        Assert.assertEquals(driver.findElement(By.xpath(secondProductNameLink)).getText(), PantsDataList.get(0));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductPriceLabel)).getText(), PantsDataList.get(1));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductSizeLabel)).getText(), PantsDataList.get(2));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductColorLabel)).getText(), PantsDataList.get(3));
        Assert.assertEquals(driver.findElement(By.xpath(secondProductSubTotalLabel)).getText(), "$74.00");
        Assert.assertEquals(driver.findElement(By.xpath(secondProductQtyField)).getAttribute("value"), JacketDataList.get(4));
        float firstProductSubTotalPrice = customMethods.stringToFloat(driver, firstProductSubTotalLabel);
        float secondProductSubTotalPrice = customMethods.stringToFloat(driver, secondProductSubTotalLabel);
        float productsSubTotal = firstProductSubTotalPrice + secondProductSubTotalPrice;
        customMethods.waitUntilTextToBePresentInXpathElement(driver, subTotalLabel, "$134.00");
        float summarySubTotal = customMethods.stringToFloat(driver, subTotalLabel);
        Assert.assertEquals(productsSubTotal, summarySubTotal);
        driver.findElement(By.xpath(estimateShippingAndTaxDropDown)).click();
        Select country = new Select(driver.findElement(By.xpath(countyField)));
        country.selectByVisibleText("Macedonia");
        driver.findElement(By.xpath(stateProvinceField)).sendKeys(stateProvinceValue);
        driver.findElement(By.xpath(zipPostalCodeField)).sendKeys(zipPostalCodeValue);
        driver.findElement(By.xpath(estimateShippingAndTaxDropDown)).click();
        customMethods.waitUntilTextToBePresentInXpathElement(driver, shippingPriceLabel, "$10.00");
        float shippingPrice = customMethods.stringToFloat(driver, shippingPriceLabel);
        float summarySubTotalPlusShippingPrice = summarySubTotal + shippingPrice;
        float orderTotal = customMethods.stringToFloat(driver, orderTotalLabel);
        Assert.assertEquals(summarySubTotalPlusShippingPrice, orderTotal);
        fileReader.CreateFile("ShippingAndTax.txt");
        fileReader.WriteToFile("ShippingAndTax.txt", stateProvinceValue + "\n" + zipPostalCodeValue + "\n" + summarySubTotal + "\n" + shippingPrice + "\n" + orderTotal);
        driver.findElement(By.xpath(proceedToCheckoutBtn)).click();
        String shippingAddressHeader = driver.findElement(By.xpath(shippingAddressHeaderElement)).getText();
        return shippingAddressHeader;
    }
}
