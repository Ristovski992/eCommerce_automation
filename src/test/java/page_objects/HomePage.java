package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public String createAnAccountLink = "/html/body/div[1]/header/div[1]/div/ul/li[3]/a";

    public String clickCreateAnAccountLink(WebDriver driver) {
        driver.findElement(By.xpath(createAnAccountLink)).click();
        String createNewCustomerHeader = driver.findElement(By.xpath("//*[@id='maincontent']/div[1]/h1/span")).getText();
        return createNewCustomerHeader;
    }
}
