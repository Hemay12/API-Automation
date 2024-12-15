package Selenium;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.apache.commons.io.FileUtils;

public class FlagDisplay {
    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Beginner/B-1.16-CaptureScreeshot.html");
        Thread.sleep(7000);

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        WebElement x= driver.findElement(By.xpath("//div[@id='flagsContainer']"));
        File f1 = x.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f1, new File ("C:\\Users\\rites\\Downloads\\\\f1.png"));
        Thread.sleep(5000);

        File f2 = x.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f2, new File ("C:\\Users\\rites\\Downloads\\f2.png"));
        Thread.sleep(5000);

        // Take a screenshot of the entire div element
        Screenshot screenshot1 = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver, x);

        // Save the screenshot
        ImageIO.write(screenshot1.getImage(), "PNG", new File("C:\\Users\\rites\\Downloads\\f3.png"));
    }

}

