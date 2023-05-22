package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateAnAccountPage {
    CustomMethods customMethods = new CustomMethods();
    FileReader fileReader = new FileReader();
    public String firstNameField = "//*[@id='firstname']";
    public String firstNameValue = customMethods.generateRandomStringWithoutNumbers(6);
    public String lastNameField = "//*[@id='lastname']";
    public String lastNameValue = customMethods.generateRandomStringWithoutNumbers(6);
    public String emailField = "//*[@id='email_address']";
    public String emailValue = customMethods.generateRandomStringWithoutNumbers(5) + "@test.com";
    public String passwordField = "//*[@id='password']";
    public String passwordValue = customMethods.generateRandomStringWithNumbers(10) + "aA!";
    public String confirmPasswordField = "//*[@id='password-confirmation']";
    public String confirmPasswordValue = passwordValue;
    public String createAnAccountBtn = "//*[@id='form-validate']/div/div[1]/button";
    public String passwordStrengthMeterField = "//*[text()='Very Strong']/../..";
    public String createAnAccountSuccessMessage = "//*[@id='maincontent']/div[1]/div[2]/div/div/div";
    public String fullNameAndEmailLabel = "//*[@id='maincontent']/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/p";

    public String fillingOutTheRegistrationForm(WebDriver driver) {
        driver.findElement(By.xpath(firstNameField)).sendKeys(firstNameValue);
        driver.findElement(By.xpath(lastNameField)).sendKeys(lastNameValue);
        driver.findElement(By.xpath(emailField)).sendKeys(emailValue);
        driver.findElement(By.xpath(passwordField)).sendKeys(passwordValue);
        Assert.assertEquals(driver.findElement(By.xpath(passwordStrengthMeterField)).getText(), "Password Strength: Very Strong");
        driver.findElement(By.xpath(confirmPasswordField)).sendKeys(confirmPasswordValue);
        fileReader.CreateFile("RegisterData.txt");
        fileReader.WriteToFile("RegisterData.txt", firstNameValue + "\n" + lastNameValue + "\n" + emailValue + "\n" + passwordValue);
        customMethods.waitUntilVisibilityOfXpathElement(driver, createAnAccountBtn);
        driver.findElement(By.xpath(createAnAccountBtn)).click();
        customMethods.waitUntilVisibilityOfXpathElement(driver, createAnAccountSuccessMessage);
        String successRegistrationMessage = driver.findElement(By.xpath(createAnAccountSuccessMessage)).getText();
        Assert.assertEquals(driver.findElement(By.xpath(fullNameAndEmailLabel)).getText(), firstNameValue + " " + lastNameValue + "\n" + emailValue);
        return successRegistrationMessage;
    }
}
