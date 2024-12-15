package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

public class MatchTheFollowing {

    @Test
    public void Match() throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Beginner/B-1.27-MatchTheFollowing.html");

        List<WebElement> boxes = driver.findElements(By.xpath("//div[@class='item ui-draggable ui-draggable-handle']"));
        WebElement number = driver.findElement(By.xpath("//div[normalize-space()='Number']"));
        WebElement stt = driver.findElement(By.xpath("//div[normalize-space()='String']"));
        WebElement alphanumeric = driver.findElement(By.xpath("//div[normalize-space()='Alphanumeric']"));

        Actions ac = new Actions(driver);
        for(WebElement x : boxes){

            String str = x.getText();
            if(str.matches("\\d+")){
                ac.dragAndDrop(x,number).build().perform();
                Thread.sleep(500);
            }
            else if(str.matches("[a-zA-Z]+")){
                ac.dragAndDrop(x,stt).build().perform();
                Thread.sleep(500);
            }
            else {
                ac.dragAndDrop(x,alphanumeric).build().perform();
                Thread.sleep(500);
            }
        }
    }
}
