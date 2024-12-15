package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class youngestPerson {

    @Test
    public void youngestPersonMethod(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Intermediate/I-2.27-YoungestPerson.html");

        List<WebElement> person = driver.findElements(By.xpath("//div[@class='container']/form/label"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy, h:mm:ss a");

        LocalDateTime youngestDOB = null;
        WebElement youngestElement = null;

        for (WebElement element : person) {
            // Extract the text, e.g., "Liam (DOB: 15/5/2021, 2:38:47 pm)"
            String text = element.getText();

            // Extract the DOB part from the text
            String dobString = text.substring(text.indexOf("DOB:") + 5, text.indexOf(")"));
            LocalDateTime dob = LocalDateTime.parse(dobString, formatter);

            // Compare DOBs to find the youngest (most recent DOB)
            if (youngestDOB == null || dob.isAfter(youngestDOB)) {
                youngestDOB = dob;
                youngestElement = element;
            }

            youngestElement.click();
        }
    }
}
