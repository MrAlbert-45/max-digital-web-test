package appointments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pre_conditions.Master;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class NavigationBarClicks {
    WebDriver driver = Master.driver;
    WebDriverWait wait;

    public NavigationBarClicks(){
        this.wait = new WebDriverWait(Master.driver,Duration.ofSeconds(20));
    }


    public static WebDriver navigation_Testing() {

        // Navigation testing by clicking multiple times.

        for (int i=0; i < 3 ; i++)
        {
            //Click on Appointment navigation
            WebElement apptsNavClick = Master.driver.findElement(By.className("ant-menu-item"));
            apptsNavClick.click();
            //System.out.println("Click on Appointment navigation successfully");
            Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


            // Click on the Analytics element
            WebElement analyticsElement = Master.driver.findElement(By.cssSelector("li.ant-menu-item[title='Analytics']"));
            analyticsElement.click();
            // Print a message indicating successful click
            //System.out.println("Clicked on Analytics navigation successfully");
            Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


            // Click on the myPatient element
            WebElement myPatientElement = Master.driver.findElement(By.cssSelector("li.ant-menu-item[title='My Patients']"));
            myPatientElement.click();
            // Print a message indicating successful click
            //System.out.println("Clicked on My Patients navigation successfully");
            Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

            // Click on the Chat element
            WebElement chatElement = Master.driver.findElement(By.cssSelector("li.ant-menu-item[title='Chat']"));
            chatElement.click();
            // Print a message indicating successful click
            //System.out.println("Clicked on Chat navigation successfully");
            Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

            // Click on the myPatient element
            WebElement notificationsElement = Master.driver.findElement(By.cssSelector("li.ant-menu-item[title='Notifications']"));
            notificationsElement.click();
            // Print a message indicating successful click
            //System.out.println("Clicked on Notifications navigation successfully");
            Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
        System.out.println("Navigation clicked testing done successfully \n 3 times click on Navigation");
        //Click on the Setting element
        WebElement settingElement = Master.driver.findElement(By.cssSelector("li.ant-menu-submenu.ant-menu-submenu-vertical"));
        settingElement.click();
        // Print a message indicating successful click
        System.out.println("Clicked on Setting navigation successfully");
        Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        System.out.println("Thank you for using us.");
        return null;
    }


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        Master.doMain(); // Calling master for login as pre-conditions
        navigation_Testing();

    } // main Function closed

}
