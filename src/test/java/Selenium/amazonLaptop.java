package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class amazonLaptop {

    @Test
    public void testAmazonCart() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.amazon.in/");

        WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
        searchBar.sendKeys("Laptop");
        searchBar.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-fixed-height'])[1]")).click();

//        Set<String> handles = driver.getWindowHandles();
//        Iterator it = handles.iterator();
//
//        String parent = (String) it.next();

        Thread.sleep(2000);
        WebElement addToCart = driver.findElement(By.xpath("(//input[@id='add-to-cart-button'])[2]"));
        addToCart.click();


    }
}
