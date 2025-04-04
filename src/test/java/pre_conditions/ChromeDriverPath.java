package pre_conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverPath {

    public static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\M033073\\AutomationSetup\\ChromeDriver\\chromedriver.exe");

        //Get ChromeOptions from PermissionHandling
        ChromeOptions options = PermissionHandling.getChromeOptions();

        //Launch browser with options
          return new ChromeDriver(options);
    }
}
