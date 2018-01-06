package com.xing.spider.spider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.xing.spider.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class ZhihuSpider extends CommonSpider {

	private WebDriver driver;
	private Jedis jedis;

	public ZhihuSpider(String url) {
		driver = new ChromeDriver();
		driver.get(url);
		jedis = RedisUtil.getJedis();
	}

	public static void main(String[] args) {
		ZhihuSpider spider = new ZhihuSpider("https://www.zhihu.com/signin");
		try {
			spider.login();
			spider.sleep();
			spider.crawList(spider);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("休眠失败!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("写入文件失败");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Md5生成失败");
		} finally {
			spider.close();
		}

	}

	public List<String> processListUrl() {

		return null;
	}

	// 爬取列表
	public void crawList(ZhihuSpider spider) throws InterruptedException, IOException, NoSuchAlgorithmException {
		this.sleep();
		String hostWindowHandle = driver.getWindowHandle();
		System.out.println(hostWindowHandle);
		List<WebElement> crawTask = driver.findElements(By.className("ContentItem-title"));
		for (int i = 0; i < crawTask.size(); i++) {
			driver.switchTo().window(hostWindowHandle);
			List<WebElement> task = driver.findElements(By.className("ContentItem-title"));
			WebElement webElement = task.get(i);
			WebElement element = webElement.findElement(By.tagName("a"));
			element.click();
			this.sleep();
			spider.processHtml();
		}
	}

	// 登录
	@Override
	public void login() throws InterruptedException {
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(this.getProperties("user"));
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(this.getProperties("pass"));
		this.sleep();
		WebElement loginButton = driver.findElement(By.className("SignFlow-submitButton"));
		loginButton.click();
	}

	// 关闭资源
	@Override
	public void close() {
		driver.quit();
		jedis.close();
	}

	// 爬取页面
	@Override
	public void processHtml() throws InterruptedException, IOException, NoSuchAlgorithmException {
		// driver.get(url);
		String currentHandle = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		WebDriver currentWindow = null;
		for (String string : set) {
			if (!currentHandle.equals(string)) {
				currentWindow = driver.switchTo().window(string);
			}
		}

		String currentUrl = driver.getCurrentUrl();
		if (!currentUrl.contains("zhuanlan")) {
			driver.findElement(By.className("QuestionMainAction")).click();
			this.sleep(3000);
			int i = 0;
			while (true) {
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				i++;
				this.sleep(2000);
				if (i==10) {
					break;
				}
				try {
					driver.findElement(By.className("QuestionAnswers-answerButton"));
					break;
				} catch (Exception e) {
					
				}
			}
			String url = driver.getCurrentUrl();
			String site = "Zhihu";
			if (!put2Redis(site, url)) {
				System.err.println("此页已经爬取~");
				if (currentWindow != null) {
					currentWindow.close();
				}
				return;
			}
			List<WebElement> list = driver.findElements(By.className("AnswerItem"));
			WebElement titleElement = driver.findElement(By.cssSelector(
					"#root > div > main > div > div:nth-child(10) > div.QuestionHeader > div.QuestionHeader-content > div.QuestionHeader-main > h1"));
			String title = titleElement.getText();
			for (WebElement webElement : list) {
				String author = webElement.findElement(By.className("AuthorInfo-head")).getText();
				String htmlText = webElement.findElement(By.className("RichContent-inner")).getText();
				this.writeText2Disk(title, author.replaceAll("\n", ""), htmlText);
			}
		} else {
			String title = driver.findElement(By.className("PostIndex-title")).getText();
			String htmlText = driver.findElement(By.className("PostIndex-content")).getText();
			String author = driver.findElement(By.className("PostIndex-author")).getText();
			this.writeText2Disk(title, author.replaceAll("\n", ""), htmlText);
		}
		if (currentWindow != null) {
			currentWindow.close();
		}
	}

}
