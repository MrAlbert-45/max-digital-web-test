package appointments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pre_conditions.Master;

import java.net.MalformedURLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddAppointment_VC {

    static WebDriverWait wait;
    static WebDriver driver = Master.driver;
    static String[] mobileNumber = {"7693072402"  , "8527428617" , "9817530091",
            "7665591889" , "7349764267" , "6260056274" ,
            "8529740967","6377893661","9599545133","8077074647","9834370602","9718310366" , "9501502560"};
                // Nitish , Arjun , Aradhna , Harsh , Agnivesh , Smriti ,
                // Aditi , Sandeep , Bhavnesh , Amit , Himanshi , Shubham

    // wait time constructor
    public AddAppointment_VC(){
        this.wait = new WebDriverWait(Master.driver, Duration.ofSeconds(10));
    }// Add appointment Constructor

    public static void entryInAppointmentSection(){

        // Login Credentials fetch from preCondition class
        System.out.println("Current Page Title Name is :" +Master.driver.getTitle());
        Master.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Wait timing for event access
        wait = new WebDriverWait(Master.driver, Duration.ofSeconds(10));

        //Current Date Handling
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateSet = currentDate.format(formatter);
        System.out.println("Current Date is before selection of appts date: >" +dateSet);

        wait = new WebDriverWait(Master.driver,Duration.ofSeconds(10));

    } //entryInAppointmentSection


    public static void addAppointmentsPerform(){
        Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //click on + icon as Add appts icon
        WebElement plusIcon = Master.driver.findElement(By.xpath("//*[@id=\"video-section-non\"]/div/section/section/main/div/div/div[3]/div/button"));
        plusIcon.click();
        System.out.println("Clicked on Add appointments page open");
        wait = new WebDriverWait(Master.driver,Duration.ofSeconds(10));

        try {
            //Verify the screen name
            String expectedName = "Add Appointment";
            String actualName = Master.driver.findElement(By.cssSelector("div.ant-space-item")).getText().trim();
            if (expectedName.equals(actualName)) {
                System.out.println("Now we are on Add Appointment screen");
            } else {

                System.out.println("Verify screen name it not as expected screen. > Fail");
            }
        } // try close
        catch (Exception addAppointmentException){
            throw new RuntimeException("Exception when not found the add Appointment Button \n " +addAppointmentException.getMessage());
        }

        Master.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }//addAppointmentsPerform,



    public static void hospitalSelection(){
        Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        // Select "Max Saket West" from the Hospital dropdown
        WebElement hospitalDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Hospital']/following-sibling::div//div[@class='ant-select-selector']")));
        hospitalDropdown.click(); // Click the drop down to open it

        WebElement hospitalDropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));
        List<WebElement> selectHospital = hospitalDropdownContainer.findElements(By.cssSelector("div.ant-select-item-option-content"));
        for (WebElement hpName : selectHospital) {
            if (hpName.getText().equals("Max Saket West")) {
                hpName.click();
                System.out.println("Max Saket West Selected");
                break;
            }
        }

    }//hospitalSelection


    public static void appointmentTypeSelection(){
        Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        try {
        // Select "VC Consultation" from the Appointment Type drop down
        WebElement apptTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Appointment Type']/following-sibling::div//div[@class='ant-select-selector']")));
        apptTypeDropdown.click(); // Click the drop-down to open it

        WebElement apptTypeDropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));
        List<WebElement> apptsType = apptTypeDropdownContainer.findElements(By.cssSelector("div.ant-select-item-option-content"));
        boolean apptypes = false;

        for (WebElement apptsTypes : apptsType) {
            if (apptsTypes.getText().equals("VC Consultation")) {
                apptsTypes.click();
                System.out.println("VC Consultation selected");
                apptypes = true;
                break; // Break the loop once the desired option is found and clicked
            }
        } // for

        // Check if the "VC Consultation" was not found
        if (!apptypes) {
            System.out.println("VC Consultation not found. Try again");
                 }
          } // try
        catch(Exception e) {
        System.out.println("Error selecting appointment type: " + e.getMessage());
        }

    } //appointmentTypeSelection

    public static void futureDateAndSlotSelection() {
            wait = new WebDriverWait(Master.driver, Duration.ofSeconds(10));
            boolean slotFound = false;

            try {
                while (!slotFound) {
                    // Open calendar
                    WebElement calendar_click = Master.driver.findElement(By.cssSelector("div.ant-picker-input"));
                    calendar_click.click();
                    System.out.println("✅ Calendar Open Successfully");

                    // Get current month & year
                    WebElement currentMMYY = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-picker-header-view")));
                    String datezone = currentMMYY.getText();
                    System.out.println("📅 Current Year and Month: " + datezone);

                    // Click Next Month Button
                    WebElement nextmonthbtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.ant-picker-header-next-btn")));
                    nextmonthbtn.click();
                    System.out.println("➡ Next month button clicked");
                    Master.driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
                    // Get new selected month
                    WebElement currentMMYY2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ant-picker-header-view")));
                    String datezone2 = currentMMYY2.getText();

                    // Select a random day
                    List<WebElement> daySelect = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.ant-picker-cell-inner")));

                    if (!daySelect.isEmpty()) {
                        Random randomDay = new Random();
                        int randomIndex = randomDay.nextInt(daySelect.size());
                        WebElement randomDayElement = daySelect.get(randomIndex);
                        randomDayElement.click();
                        System.out.println("📅 Selected random date: " + randomDayElement.getText() + " " + datezone2);
                        slotFound = true; // ✅ Mark as true once a date is selected
                    } else {
                        System.out.println("❌ No available dates, trying again...");
                    }
                }
            } catch (Exception e) {
                System.out.println("❌ Error selecting date: " + e.getMessage());
            }

            // Slot selection process
            try {
                WebElement slotevent = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[text()='Appointment Time']/following-sibling::div//div[@class='ant-select-selector']")));
                slotevent.click();
                System.out.println("🕒 Clicked on Slot Selection dropdown");

                WebElement slotDropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));
                List<WebElement> timeSlots = slotDropdownContainer.findElements(By.cssSelector("div.ant-select-item-option-content"));

                if (!timeSlots.isEmpty()) {
                    Random random = new Random();
                    int slotIndex = random.nextInt(timeSlots.size());
                    WebElement selectedSlot = timeSlots.get(slotIndex);
                    ((JavascriptExecutor) Master.driver).executeScript("arguments[0].scrollIntoView(true);", selectedSlot);
                    selectedSlot.click();
                    System.out.println("✅ Selected time slot: " + selectedSlot.getText());
                } else {
                    System.out.println("❌ No time slots available");
                }
            } catch (Exception e) {
                System.out.println("❌ Error selecting slot: " + e.getMessage());
            }
        }

    public static void enterMobileNumber() throws InterruptedException {
        Random Nrandom = new Random();
        int arrayListMobile = Nrandom.nextInt(mobileNumber.length);
        String newMobileNumber = mobileNumber[arrayListMobile];
        WebElement pMobile = Master.driver.findElement(By.xpath("//*[@id=\"video-section-non\"]/div/section/section/main/div/div[2]/div/div[2]/div/div[2]/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(pMobile));
        pMobile.sendKeys(newMobileNumber);
        System.out.println("Mobile Number Entered Successfully");
        Thread.sleep(10000);

    } //enterMobileNumber


    public static void randomPatientSelection() throws InterruptedException{
        Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebElement choosePatient = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//label[text()='Choose Patient']/following-sibling::div//div[@class='ant-select-selector']")));
        choosePatient.click();
        System.out.println("Clicked on Choose Patient");

        WebElement patientList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));
        List<WebElement> patientSelect = patientList.findElements(By.cssSelector("div.ant-select-item-option-content"));
        Random randomP = new Random();
        int patientIndex = randomP.nextInt(patientSelect.size());
        WebElement selectP = patientSelect.get(patientIndex);
        ((JavascriptExecutor) Master.driver).executeScript("arguments[0].scrollIntoView(true)", selectP);
        selectP.click();
        System.out.println("Patient Selected " +selectP.getText());
        Master.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
         Thread.sleep(5000);
    } //randomPatientSelection

    public static void submitButton(){
        WebElement submitBtn = Master.driver.findElement(By.xpath("//*[@id=\"video-section-non\"]/div/section/section/main/div/div[2]/div/div[3]/button/span"));
        submitBtn.click();
        System.out.println("Appts Submitted successfully");

        //Waiting for Success message as assertion
        

    }

    public static void main(String[] args) throws InterruptedException{
        Master.doMain();

        entryInAppointmentSection(); // Calling PreTests
        addAppointmentsPerform();
        hospitalSelection();
        appointmentTypeSelection();
        futureDateAndSlotSelection();
        enterMobileNumber();
        randomPatientSelection();
        submitButton();

    } // main

} // Add appointment class


