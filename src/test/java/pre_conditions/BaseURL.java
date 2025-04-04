package pre_conditions;

import org.openqa.selenium.WebDriver;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BaseURL {

    private static String defaultURL = "";

    public static WebDriver loginAccess() {

        // Get ChromeDriver with Permissions
        WebDriver driver = ChromeDriverPath.getChromeDriver();

        driver.manage().window().maximize();

        // Take user input
        Scanner scanInput = new Scanner(System.in);
        Timer t1 = new Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (defaultURL.isEmpty()) {
                    driver.get("https://max-web-beta.maxhealthcaredigital.com/max");
                    System.out.println("No input received. Launching MAX by default...\nNote : >> Test cases will not execute until not given any input ");
                }
            }
        }, 10000); // 10-second timer

        System.out.println("Enter 'MAX' or 'THB' to launch a specific URL (default is MAX):");
        String inputURL = scanInput.nextLine().trim();
        t1.cancel(); // Stop timer after input

        if (inputURL.equalsIgnoreCase("THB")) {
            driver.get("https://maxalpha-medi.maxhospitals.in");
            System.out.println("THB Max Alpha URL Launched");

        } else if (inputURL.equalsIgnoreCase("MAX")) {
            driver.get("https://max-web-beta.maxhealthcaredigital.com/max");
            System.out.println("MAX Beta URL Launched");
        } else {
            // Default case if user input is invalid
            driver.get("https://max-web-beta.maxhealthcaredigital.com/max");
            System.out.println("Invalid input. Defaulting to MAX Beta URL.");
        }

        return driver;
    }

}
