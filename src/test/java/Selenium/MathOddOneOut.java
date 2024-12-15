package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MathOddOneOut {

    @Test
    public void oddOne(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://softwaretestingpro.github.io/Automation/Beginner/B-1.24-MathOddOneOut.html");

        List<WebElement> boxes = driver.findElements(By.xpath("//button[@class = 'math-button']"));

        for( WebElement s : boxes){
            String str = s.getText();
            System.out.println(str);

            String [] parts = str.split(" ");
            int operand = Integer.parseInt(parts[0]);
            String operation = parts[1];
            int operand2 = Integer.parseInt(parts[2]);
            int result = Integer.parseInt(parts[4]);

            int calculated=0;

            if(operation.equals("+")){
                calculated = operand + operand2;
            }

            if(calculated != result){
                System.out.println("Odd one found : "+str);
                s.click();
                break;
            }
         }
    }
}
