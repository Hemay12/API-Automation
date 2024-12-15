package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class validateUpperCaseStrings {

    @Test
    public void UpperStrings(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Beginner/B-1.26-CaseValidation.html");

        List<WebElement> string = driver.findElements(By.xpath("//div[@class='checkbox-container']/div/label"));

        for(WebElement upper : string){
            String s = upper.getText();
            System.out.println(s);

            if(s.equals(s.toUpperCase())){
                WebElement check = upper.findElement(By.xpath(".//input[@type='checkbox']"));
                check.click();
            }
        }

        driver.findElement(By.xpath("//button[@class='validate-btn']")).click();
    }
}
