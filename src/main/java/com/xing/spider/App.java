package com.xing.spider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://qzone.qq.com/");
		
		Thread.sleep(3000);
		WebDriver frame = driver.switchTo().frame("login_frame");
		
		WebElement element = driver.findElement(By.cssSelector("#switcher_plogin"));
		element.click();
		
		Thread.sleep(3000);
		driver.findElement(By.id("u")).sendKeys("1217167420");
		Thread.sleep(1000);
		driver.findElement(By.id("p")).sendKeys("jasmine0128");
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.id("login_button"));
		submit.click();
		Thread.sleep(3000);

	}
}
