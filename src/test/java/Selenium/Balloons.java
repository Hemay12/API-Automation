package Selenium;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class Balloons {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://softwaretestingpro.github.io/Automation/Beginner/B-1.13-Balloons.html");
        driver.manage().window().maximize();

        long startTime = System.currentTimeMillis();
        long duration = 20000; // 20 seconds duration

        while (System.currentTimeMillis() - startTime < duration){
            List<WebElement> balloons = driver.findElements(By.xpath("//div[@class='balloon']"));

            for (WebElement balloon : balloons) {
                try {
                    String x = balloon.getText();
                    if (!x.isEmpty()) { // Ensure the balloon has text
                        int integer = Integer.parseInt(x);
                        if (integer % 2 == 0) {
                            wait.until(ExpectedConditions.elementToBeClickable(balloon));
                            balloon.click();
                            System.out.println("Clicked on balloon with number: " + integer);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error while interacting with a balloon: " + e.getMessage());
                }
            }
            Thread.sleep(300); // Wait for 300ms before checking again
        }
    }
}

