package pre_conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DoctorLoginCredentials {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String MOBILE_NUMBER = "9873349613";
    String[] doctorMobileNumber = {"9873349613" ,"9818403138" ,"5555553311"};

    // Constructor to pass WebDriver instance
    public DoctorLoginCredentials(WebDriver InstanceDriver) {
        this.driver = InstanceDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void mobileLogin() {
        System.out.println("First page title: " + driver.getTitle());


        // Click on Doctor Login button
        WebElement doctorLoginClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/button[3]")));
        doctorLoginClick.click();

        //Enter Mobile number from doctor array
        Random Dmobile = new Random();
        int doctorMobile = Dmobile.nextInt(doctorMobileNumber.length);
        String randomDoctorLogin = doctorMobileNumber[doctorMobile];

        // Enter Mobile Number
        WebElement enterMobileNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("number-login_username")));
        enterMobileNumber.sendKeys(randomDoctorLogin);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Click Login Button
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"number-login\"]/div[2]/div[2]/button"));
        loginBtn.click();
    }

    public void enterOTP() {
        WebElement enterOTP1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[1]/div/div/div/div/div/div[1]/input")));
        enterOTP1.sendKeys("3");
        // Second Input
        WebElement enterOTP2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[1]/div/div/div/div/div/div[2]/input")));
        enterOTP2.sendKeys("2");
        // Third Input
        WebElement enterOTP3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[1]/div/div/div/div/div/div[3]/input")));
        enterOTP3.sendKeys("1");
        // Fourth Input
        WebElement enterOTP4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[1]/div/div/div/div/div/div[4]/input")));
        enterOTP4.sendKeys("5");
        // Fifth Input
        WebElement enterOTP5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[1]/div/div/div/div/div/div[5]/input")));
        enterOTP5.sendKeys("6");
        // Last input
        WebElement enterOTP6 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[1]/div/div/div/div/div/div[6]/input"));
        enterOTP6.sendKeys("4");
        wait.until(ExpectedConditions.textToBePresentInElementValue(enterOTP6, "4"));

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]/div[2]/div/div/form/div[3]/div/button")));
        submitButton.click();

        System.out.println("Logged in successfully");
    }

    public void loginMaster() {
        mobileLogin();
        enterOTP();
    }
}
