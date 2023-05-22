package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class CustomMethods {
    String generateRandomStringWithNumbers(int l) {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder s = new StringBuilder(l);
        int i;
        for (i = 0; i < l; i++) {
            int ch = (int) (AlphaNumericStr.length() * Math.random());
            s.append(AlphaNumericStr.charAt(ch));
        }
        return s.toString();
    }

    public String generateRandomStringWithoutNumbers(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < length; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(c);
        }
        return sb.toString();
    }

    public void waitUntilVisibilityOfXpathElement(WebDriver driver, String element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    public void waitUntilTextToBePresentInXpathElement(WebDriver driver, String element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(element), text));
    }

    public void waitUntilXpathElementToBeClickable(WebDriver driver, String element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
    }

    public float stringToFloat(WebDriver driver, String element) {
        String getTextFromElement = driver.findElement(By.xpath(element)).getText();
        getTextFromElement = getTextFromElement.replace("$", "");
        float text = Float.parseFloat(getTextFromElement);
        return text;
    }
}
