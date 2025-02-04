package com.batch.scroll;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollUp {
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollTo(0,document.body.scrollHeight)";
        js.executeScript(script);
        Thread.sleep(3000);
        js.executeScript("window.scrollTo(0,0)");
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeCrossBrowser() {
        driver.quit();
    }
}

