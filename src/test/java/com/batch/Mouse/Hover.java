package com.batch.Mouse;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Hover {
    String url = "https://www.browserstack.com/guide/mouse-hover-in-selenium";
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
        String expectedTitle = "How to perform Mouse Hover Action in Selenium | BrowserStack";
        String actualTitle = driver.getTitle();
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        softAssert.assertEquals(actualTitle, expectedTitle);
        WebElement name = driver.findElement(By.xpath("//button[@id='developers-dd-toggle']"));
        actions.moveToElement(name).perform();

        driver.findElement(By.xpath("//a[@title='Documentation']")).click();

        Thread.sleep(5000);

        softAssert.assertAll();

    }

    @AfterSuite
    public void closeCrossBrowser() {
        driver.quit();
    }
}

