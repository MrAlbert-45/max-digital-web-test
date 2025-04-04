package appointments;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pre_conditions.Master;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class AddNewPatient {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    String newCreatedPatientName;

    // Constructor to initialize WebDriver and WebDriverWait
    public AddNewPatient() {
        this.driver = Master.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Reduced from 300s to 30s
        this.js = (JavascriptExecutor) driver;

    }

    // Enter new mobile number
    public void enterNewMobileNumber() throws InterruptedException {
        // Click on Plus icon
        WebElement plusIcon = driver.findElement(By.xpath("//*[@id=\"video-section-non\"]/div/section/section/main/div/div/div[3]/div/button"));
        plusIcon.click();
        System.out.println("Clicked on Add appointments page open");

        Thread.sleep(5000); // Consider using WebDriverWait instead of Thread.sleep()

        // Enter New Mobile Number
        WebElement enterMobileNumber = driver.findElement(By.cssSelector("input.ant-input.ant-input-lg"));
        enterMobileNumber.sendKeys("9519980611");
        System.out.println("Mobile Entered Successfully");
        Thread.sleep(5000);

        // Define the search button
        By searchButtonLocator = By.cssSelector("button.ant-btn.ant-btn-default.ant-btn-lg.ant-btn-icon-only.ant-input-search-button");
        // Wait until the search button is NOT in the loading state
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(searchButtonLocator, "class", "ant-btn-loading")));
        System.out.println("Search button is no longer loading.");
    }

    // Select Add New Patient
    public void selectAddNewPatient() throws InterruptedException {

        WebElement waitingSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ant-btn.ant-btn-default.ant-btn-lg.ant-btn-icon-only.ant-input-search-button")));
        // Select patient dropdown
        WebElement choosePatient = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Choose Patient']/following-sibling::div//div[@class='ant-select-selector']")));
        choosePatient.click();
        System.out.println("Clicked on Choose Patient");

        // Wait for patient dropdown to appear
        WebElement patientList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));

        // Find all patient options inside this last active element
        List<WebElement> patientSelect = patientList.findElements(By.cssSelector("div.ant-select-item-option-content"));

        boolean patientFound = false; // Flag to track if 'Add New Patient' is found

        for (int i = 0; i < patientSelect.size(); i++) {
            WebElement patientOption = patientSelect.get(i);
            // Scroll to the current element
            js.executeScript("arguments[0].scrollIntoView(true);", patientOption);
            patientFound = true;

            // Check if the element text matches 'Add New Patient'
            if (patientOption.getText().equals("Add New Patient")) {
                wait.until(ExpectedConditions.elementToBeClickable(patientOption));
                js.executeScript("arguments[0].click();", patientOption);
                System.out.println("Clicked on 'Add New Patient'");
                break; // Exit loop after clicking
            }
        }

        // If not found, print message
        if (!patientFound) {
            System.out.println("'Add New Patient' option was not found.");
        }
        Thread.sleep(3000);

    } //SelectAddNewPatient


    public void newPatientEntryForm() throws InterruptedException {

        LocalDate currentDate = LocalDate.now().minus(2, ChronoUnit.DECADES);
        DateTimeFormatter currentDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String exactDate = currentDateFormat.format(currentDate);

        WebElement formAccess = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-col.ant-col-24.white-background.padding20")));
        formAccess.click();

        //CurrentDate for first name:
        LocalDate firstNameCurrentDate = LocalDate.now();
        DateTimeFormatter fNameDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String firstNameDate = fNameDate.format(firstNameCurrentDate);

        //Current Time for last Name
        LocalTime lastNameCurrentTime = LocalTime.now();
        DateTimeFormatter lNameTime = DateTimeFormatter.ofPattern("hhmmss");
        String lastNameTime = lNameTime.format(lastNameCurrentTime);


        // Enter First Name
        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.id("basic_first_name")));
        firstName.sendKeys("Automation ~" + firstNameDate);

        // Enter Last Name (Fixed Selection)
        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.id("basic_last_name")));
        lastName.sendKeys("Testing ~ " + lastNameTime);

        newCreatedPatientName = "Automation ~" + firstNameDate + "Testing ~ " + lastNameTime;

        //basic_email
        WebElement emailID = wait.until(ExpectedConditions.elementToBeClickable(By.id("basic_email")));
        emailID.sendKeys("xing.hu@albert.com");
        System.out.println("Email id filler successfully entered");

        //Date of Birth >
        WebElement newUserDOB = wait.until(ExpectedConditions.elementToBeClickable(By.id("basic_dob")));
        newUserDOB.sendKeys(exactDate);
        WebElement dateChildElementClick = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-picker-cell-inner")));
        dateChildElementClick.click();
        System.out.println("Date of Birth Entered Successfully");

        // Click the dropdown to open options
        WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@for='basic_gender_id']/following::div[@class='ant-select-selector'][1]")));
        genderDropdown.click();
        System.out.println("Clicked Dropdown");
        WebElement dropdownElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));
        List<WebElement> genderOptions = dropdownElements.findElements(By.cssSelector("div.ant-select-item-option-content"));
        if (!genderOptions.isEmpty()) {
            Random random = new Random();
            int genderIndex = random.nextInt(genderOptions.size());
            WebElement selectedGender = genderOptions.get(genderIndex);
            wait.until(ExpectedConditions.elementToBeClickable(selectedGender)).click();
            System.out.println("Selected Gender: " + selectedGender.getText());
        } else {
            System.out.println("Please check manually");
        }

        //Choose Nationality
        WebElement nationalitySelection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='basic_nationality_id']/following::div[@class='ant-select-selector'][1]")));
        nationalitySelection.click();
        System.out.println("Choose Nationality");

        //Select Nationality as Indian
        WebElement indianOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.ant-select-item-option[title='indian']")));
        indianOption.click();
        System.out.println("Selected Country: " + indianOption.getText());

        //Select state and district.
        WebElement stateSelectList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='basic_addresses_city_id']/following::div[@class='ant-form-item-control-input-content'][1]")));
        stateSelectList.click();

        // Wait until state options are visible
        List<WebElement> selectState = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("ul.ant-cascader-menu:nth-child(1) > li.ant-cascader-menu-item")));

        if (!selectState.isEmpty()) {
            // Select a random state
            Random random = new Random();
            int randomIndex = random.nextInt(selectState.size());
            WebElement selectAnyState = selectState.get(randomIndex);
            String extractState = selectAnyState.getText().trim();
            // Click the selected state

            wait.until(ExpectedConditions.elementToBeClickable(selectAnyState));
            js.executeScript("arguments[0].click();", selectAnyState);
            // Wait until district options (child elements) are visible AFTER selecting the state
            List<WebElement> selectDistrict = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector("ul.ant-cascader-menu:nth-child(2) > li.ant-cascader-menu-item")));  // Ensure this targets the correct district elements
            if(!selectDistrict.isEmpty()) {
                int randomDistrictIndex = random.nextInt(selectDistrict.size());
                WebElement selectAnyDistrict = selectDistrict.get(randomDistrictIndex);
                // Click the selected district
                Thread.sleep(2000);
                js.executeScript("arguments[0].click();", selectAnyDistrict);
                System.out.println("Selected State with Corresponding District: " + extractState + " / " + selectAnyDistrict.getText());
            } else {
                System.out.println("No districts found after selecting state.");
            }
        } else {
            System.out.println("No states found in the dropdown.");
        }

        //enter postal code :
        WebElement enterPinNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("basic_addresses_pincode")));
        enterPinNumber.sendKeys("122001");

        //enter Address >> Max Hospital Gurugram

        String hospitalAddress = "B Block, Sushant Lok 1, Near Huda City Centre MF Husain Marg Near Huda City Centre, Sector 43, Gurugram";
        WebElement enterHospitalAddress = wait.until(ExpectedConditions.elementToBeClickable(By.id("basic_addresses_address_line_1")));
        enterHospitalAddress.sendKeys(hospitalAddress);
        Thread.sleep(5000);

        //Submit for new added patient

        WebElement newPatientSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#basic > div.ant-form-item > div > div > div > div > button")));
        newPatientSubmitBtn.click();
        System.out.println("Clicked on New patient submit button");
    }


    public void verifyNewPatientInList() throws InterruptedException{
        Thread.sleep(5000);
        WebElement waitingSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ant-btn.ant-btn-default.ant-btn-lg.ant-btn-icon-only.ant-input-search-button")));
        // Select patient dropdown
        WebElement choosePatient = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Choose Patient']/following-sibling::div//div[@class='ant-select-selector']")));
        choosePatient.click();
        System.out.println("Again clicked on Choose Patient");

        // Wait for patient dropdown to appear
        WebElement patientList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ant-select-dropdown:not([class*='hidden'])")));

        // Find all patient options inside this last active element
        List<WebElement> patientSelect = patientList.findElements(By.cssSelector("div.ant-select-item-option-content"));
            boolean isPatientFind = false;
            for (WebElement patient : patientSelect) {
                wait.until(ExpectedConditions.elementToBeClickable(patient));
                js.executeScript("arguments[0].scrollIntoView(true)", patient);
                Thread.sleep(3000);
                if (patient.getText().contains(newCreatedPatientName)) {
                    System.out.println("New Patient Created " + newCreatedPatientName);
                    isPatientFind = true;
                }
            }
                if(!isPatientFind){
                    System.out.println("New Patient not created");
                }
    } //verifyNewPatientInList()



        // Main function to execute the test
        public static void main(String[]args) throws InterruptedException {

            Master.doMain();
            AddNewPatient addPatient = new AddNewPatient(); // Create an instance
            addPatient.enterNewMobileNumber();
            addPatient.selectAddNewPatient();
            addPatient.newPatientEntryForm();
            addPatient.verifyNewPatientInList();

        }

} //AddNewPatient
