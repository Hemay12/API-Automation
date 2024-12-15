package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class compareImages {

    @Test
    public void imageCompare(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Intermediate/I-2.28-CompareImages.html");


        WebElement x = driver.findElement(By.xpath("//div[@class='image-container']/div[1]"));
        WebElement y = driver.findElement(By.xpath("//div[@class='image-container']/div[2]"));
        WebElement yes = driver.findElement(By.xpath("//button[@onclick='checkAnswer(true)']"));
        WebElement no = driver.findElement(By.xpath("//button[@onclick='checkAnswer(false)']"));

        System.out.println(x.getAttribute("class"));
        System.out.println(y.getAttribute("class"));

        if(x.getAttribute("class").equals(y.getAttribute("class"))){
            yes.click();
        }
        else {
            no.click();
        }
    }

}
