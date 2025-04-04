package pre_conditions;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class Master {

    public static WebDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException{
        doMain();
    }

    public static void doMain() {
        if (driver == null){
            // Step 1: Launch ChromeDriver with Permission Handling
            try {
                driver = BaseURL.loginAccess();
            } catch (Exception e){
                System.out.println("Catch error " +e.getMessage());
                throw new RuntimeException(e);
            }

            try {
                // Step 2: Execute Doctor Login
                DoctorLoginCredentials doctorLogin = new DoctorLoginCredentials(driver);
                doctorLogin.loginMaster();

            } catch (Exception d){
                throw new RuntimeException();
            } // catch
        } // if closed
    }

}
