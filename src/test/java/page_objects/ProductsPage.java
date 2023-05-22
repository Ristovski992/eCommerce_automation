package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductsPage {
    FileReader fileReader = new FileReader();
    CustomMethods customMethods = new CustomMethods();

    public String manBtnMenu = "//*[@id='ui-id-5']";
    public String jacketsLink = "//*[@id='maincontent']/div[4]/div[2]/div[2]/div/ul[1]/li[2]/a";
    public String jacketsProduct = "//*[@id='maincontent']/div[3]/div[1]/div[3]/ol/li[4]";
    public String jacketsProductSizeBtn = "//*[@id='option-label-size-143-item-168']";
    public String jacketsProductColorPicker = "//*[@id='option-label-color-93-item-49']";
    public String addToCartBtn = "//*[@id='product-addtocart-button']";
    public String successMessage = "//*[@id='maincontent']/div[1]/div[2]/div/div/div";
    public String pantsLink = "//*[@id='maincontent']/div[4]/div[2]/div[2]/div/ul[2]/li[1]/a";
    public String pantsProduct = "//*[@id='maincontent']/div[3]/div[1]/div[3]/ol/li[2]";
    public String pantsProductSizeBtn = "//*[@id='option-label-size-143-item-176']";
    public String pantsProductColorPicker = "//*[@id='option-label-color-93-item-51']";
    public String cartIcon = "/html/body/div[1]/header/div[2]/div[1]/a/span[2]/span";
    public String viewAndEditCartLink = "//*[text()='View and Edit Cart']";
    public String shoppingCartHeaderElement = "//*[@id='maincontent']/div[1]/h1/span";
    public String Availability = "//*[@id='maincontent']/div[2]/div/div[1]/div[3]/div[2]/div[1]/span";

    public String addProducts(WebDriver driver) {
        driver.findElement(By.xpath(manBtnMenu)).click();
        driver.findElement(By.xpath(jacketsLink)).click();
        driver.findElement(By.xpath(jacketsProduct)).click();
        Assert.assertEquals(driver.findElement(By.xpath(Availability)).getText(), "IN STOCK");
        driver.findElement(By.xpath(jacketsProductSizeBtn)).click();
        driver.findElement(By.xpath(jacketsProductColorPicker)).click();
        String jacketName = driver.findElement(By.xpath("//*[@id='maincontent']/div[2]/div/div[1]/div[1]/h1/span")).getText();
        String jacketPrice = driver.findElement(By.xpath("//*[@id='product-price-382']/span")).getText();
        String jacketSizeNum = driver.findElement(By.xpath(jacketsProductSizeBtn)).getText();
        String jacketColor = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[4]/form/div[1]/div/div/div[2]/div/div[1]")).getAttribute("aria-label");
        String jacketQty = driver.findElement(By.xpath("//*[@id='qty']")).getAttribute("value");
        fileReader.CreateFile("JacketData.txt");
        fileReader.WriteToFile("JacketData.txt", jacketName + "\n" + jacketPrice + "\n" + jacketSizeNum + "\n" + jacketColor + "\n" + jacketQty);
        customMethods.waitUntilVisibilityOfXpathElement(driver, addToCartBtn);
        driver.findElement(By.xpath(addToCartBtn)).click();
        String firstProductAdded = driver.findElement(By.xpath(successMessage)).getText();
        Assert.assertEquals(firstProductAdded, "You added Typhon Performance Fleece-lined Jacket to your shopping cart.");
        Assert.assertEquals(driver.findElement(By.xpath(cartIcon)).getText(), "1");
        driver.findElement(By.xpath(manBtnMenu)).click();
        driver.findElement(By.xpath(pantsLink)).click();
        driver.findElement(By.xpath(pantsProduct)).click();
        Assert.assertEquals(driver.findElement(By.xpath(Availability)).getText(), "IN STOCK");
        driver.findElement(By.xpath(pantsProductSizeBtn)).click();
        driver.findElement(By.xpath(pantsProductColorPicker)).click();
        String pantsName = driver.findElement(By.xpath("//*[@id='maincontent']/div[2]/div/div[1]/div[1]/h1/span")).getText();
        String pantsPrice = driver.findElement(By.xpath("//*[@id='product-price-867']/span")).getText();
        String pantsSizeNum = driver.findElement(By.xpath(pantsProductSizeBtn)).getText();
        String pantsColor = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[4]/form/div[1]/div/div/div[2]/div/div[2]")).getAttribute("aria-label");
        String pantsQty = driver.findElement(By.xpath("//*[@id='qty']")).getAttribute("value");
        fileReader.CreateFile("PantsData.txt");
        fileReader.WriteToFile("PantsData.txt", pantsName + "\n" + pantsPrice + "\n" + pantsSizeNum + "\n" + pantsColor + "\n" + pantsQty);
        driver.findElement(By.xpath(addToCartBtn)).click();
        String secondProductAdded = driver.findElement(By.xpath(successMessage)).getText();
        Assert.assertEquals(secondProductAdded, "You added Aether Gym Pant to your shopping cart.");
        Assert.assertEquals(driver.findElement(By.xpath(cartIcon)).getText(), "2");
        driver.findElement(By.xpath(cartIcon)).click();
        driver.findElement(By.xpath(viewAndEditCartLink)).click();
        String shoppingCartHeader = driver.findElement(By.xpath(shoppingCartHeaderElement)).getText();
        return shoppingCartHeader;
    }
}
