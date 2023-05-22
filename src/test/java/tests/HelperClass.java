package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class HelperClass {
    ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        driver = new ChromeDriver(ops);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
