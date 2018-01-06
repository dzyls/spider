package com.xing.spider.spider;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class QzoneSpider extends CommonSpider {

	private WebDriver driver;

	public QzoneSpider(String url) {
		driver = new ChromeDriver();
		driver.get(url);
	}

	public static void main(String[] args) {

		QzoneSpider spider = new QzoneSpider("https://qzone.qq.com/");
		try {
			spider.login();
			spider.clickLikeButton();
		} catch (InterruptedException e) {
			System.err.println("休眠失败!");
		}
		
	}

	//登录
	@Override
	public void login() throws InterruptedException {
		driver.switchTo().frame("login_frame");
		WebElement element = driver.findElement(By.cssSelector("#switcher_plogin"));
		element.click();
		this.sleep();
		driver.findElement(By.id("u")).sendKeys(this.getProperties("user"));
		driver.findElement(By.id("p")).sendKeys(this.getProperties("pass"));
		this.sleep(500);
		WebElement submit = driver.findElement(By.id("login_button"));
		submit.click();
		this.sleep();
	}
	
	//点赞
	public void clickLikeButton() throws InterruptedException {
		List<WebElement> elements = driver.findElements(By.className("qz_like_btn_v3"));
		System.out.println(elements.size());
		for (WebElement webElement : elements) {
			if (!webElement.getAttribute("class").contains("item-on")) {
				webElement.click();
				this.sleep();
			}
		}
		
	}
	
	
	
	// 结束时关闭driver
	@Override
	public void close() {
		// TODO Auto-generated method stub
		driver.close();
	}

	@Override
	public void processHtml() {
		// TODO Auto-generated method stub
		
	}

}
