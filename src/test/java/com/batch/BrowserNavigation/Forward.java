package com.batch.BrowserNavigation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Forward {
    String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
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
        Thread.sleep(3000);
        driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/links.php");
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().forward();
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeCrossBrowser() {
        driver.quit();
    }
}

