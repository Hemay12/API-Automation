package Selenium;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioList {
    public static void main (String [] args){

        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Beginner/B-1.07-RadioList.html");

        List<WebElement> labels =driver.findElements(By.xpath("//label[@class='form-check-label']"));

        int i=0;
        for(WebElement label : labels){
            String text = label.getText();
            if(text.equals("India")){
                WebElement parent = label.findElement(By.xpath(".."));
                parent.click();
                break;
            }
        }
    }
}
