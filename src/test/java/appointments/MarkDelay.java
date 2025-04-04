package appointments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pre_conditions.Master;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MarkDelay {

/*Go to current Date verify only Mark Delay feature be there
* Click on Mark Delay Apply any random time > should show error message
* Mark Delay should done on current date if shift time is not lapse.
* Able to do mark delay on future date any shift*/
    public static void markDelay(){

        LocalTime currentMarkDelayTime = LocalTime.now();
        DateTimeFormatter delayDate_inFormat = DateTimeFormatter.ofPattern("hh:mm");
        String currentTimeForMarkDelay = delayDate_inFormat.format(currentMarkDelayTime);
        System.out.println("Current Time is "+currentTimeForMarkDelay);

        //Click on Hospital with OPD count > eg : Saket West(3)
        WebElement hospitalCursur = Master.driver.findElement(By.cssSelector("span.cursor.underline"));
        System.out.println("Clicked");



        WebElement apptsNavClick = Master.driver.findElement(By.className("ant-menu-item"));
        apptsNavClick.click();


    }
    public static void main(String[] args){
        Master.doMain();
        markDelay();
    }



} //MarkDelay
