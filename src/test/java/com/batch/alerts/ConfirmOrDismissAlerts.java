package com.batch.alerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConfirmOrDismissAlerts {
	
	String url = "https://www.tutorialspoint.com/selenium/practice/alerts.php";
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
	
	@Test(priority = 0)
	public void openUrl() throws InterruptedException {
		driver.get(url);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(3000);
	}

	@Test(priority = 1)
	public void simpleAlert() throws InterruptedException{
		WebElement element = driver.findElement(By.xpath("//button[normalize-space()='Alert']"));
		element.click();
		Thread.sleep(3000);

		Alert alert =driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
	}
	@Test(priority = 2)
	public void rejectAlert() throws InterruptedException{
		WebElement element = driver.findElement(By.xpath("//button[@onClick='myDesk()']"));
		element.click();
		Thread.sleep(3000);

		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		Thread.sleep(3000);

	}
	
	@AfterSuite
	public void closeCrossBrowser() {
		driver.quit();
	}

}
