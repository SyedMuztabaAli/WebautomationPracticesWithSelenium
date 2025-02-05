package com.batch.frames;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FrameLocateById {
    String url = "https://www.hyrtutorials.com/p/frames-practice.html";
    String browser = System.getProperty("browser","chrome");
    WebDriver driver;

    @BeforeSuite
    public void startBrowser() {


        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();

//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless=new");
//			driver = new ChromeDriver(options);
            driver = new ChromeDriver();
        }
        else if(browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
    }

    @Test
    public void openUrl() throws InterruptedException {
        driver.get(url);
        Thread.sleep(5000);
        driver.switchTo().frame("frm2");
        WebElement name = driver.findElement(By.xpath("//input[@id='firstName']"));
        name.sendKeys("John Doe");
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeCrossBrowser() {
        driver.quit();
    }
}

