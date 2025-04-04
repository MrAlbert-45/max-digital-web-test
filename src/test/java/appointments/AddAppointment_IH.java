package appointments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pre_conditions.Master;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static appointments.AddAppointment_VC.*;

public class AddAppointment_IH {

    static WebDriverWait wait;
    static WebDriver driver = Master.driver;


    public static void appointmentTypeIH() {
        Master.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            // Select "VC Consultation" from the Appointment Type drop down
            WebElement apptTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='Appointment Type']/following-sibling::div//div[@class='ant-select-selector']")));
            apptTypeDropdown.click(); // Click the drop-down to open it

            WebElement apptTypeDropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));
            List<WebElement> apptsType = apptTypeDropdownContainer.findElements(By.cssSelector("div.ant-select-item-option-content"));
            boolean apptypes = false;

            for (WebElement apptsTypes : apptsType) {
                if (apptsTypes.getText().equals("In Hospital")) {
                    apptsTypes.click();
                    System.out.println("In Hospital selected");
                    apptypes = true;
                    break; // Break the loop once the desired option is found and clicked
                }
            } // for

            // Check if the "In Hospital" was not found
            if (!apptypes) {
                System.out.println("VC Consultation not found. Try again");
            }
        } // try
        catch (Exception e) {
            System.out.println("Error selecting appointment type: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Master.doMain();
        AddAppointment_VC.entryInAppointmentSection(); // Calling PreTests
        AddAppointment_VC.addAppointmentsPerform();
        AddAppointment_VC.hospitalSelection();
        appointmentTypeIH();
        AddAppointment_VC.futureDateAndSlotSelection();
        AddAppointment_VC.enterMobileNumber();
        AddAppointment_VC.randomPatientSelection();
        AddAppointment_VC.submitButton();

    } // main

} //AddAppointment_IH
