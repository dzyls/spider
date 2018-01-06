package com.xing.spider.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;

import com.xing.spider.util.RedisUtil;

import redis.clients.jedis.Jedis;


public abstract class CommonSpider {
	
	private Properties properties;

	public CommonSpider() {
		
		properties = new Properties();
		try { 
			properties.load(CommonSpider.class.getClassLoader().getResourceAsStream("test.properties"));
		} catch (FileNotFoundException e) {
			System.err.println("找不到properties文件!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("properties文件读取异常!");
			e.printStackTrace();
		}
		System.setProperty(this.getProperties("driverName"),
				this.getProperties("driverPath"));
	}
	
	
	public  void sleep() throws InterruptedException {
		sleep(randomSleepTime());
	}
	
	public  void sleep(int sleepTime) throws InterruptedException {
		Thread.sleep(sleepTime);
	}
	
	public  int randomSleepTime() {
		return new Random().nextInt(4500)+500;
	}
	
	
	public String getProperties(String key) {
		return properties.getProperty(key);
	}
	
	public abstract void login() throws InterruptedException;
	
	public abstract void close();
	
	
	// 将文章写入文件中
	public void writeText2Disk(String filePath,String title,String context) throws IOException {
		File file = new File("D:\\test\\mygit\\"+filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file+"\\"+title+".txt"));
		writer.write(context);
		writer.close();
	}
	
	public boolean put2Redis(String site,String url) throws NoSuchAlgorithmException {
		Jedis jedis = RedisUtil.getJedis();
		String key = site +"_"+ url;
		if (jedis.exists(key)) {
			return false;
		} else {
			jedis.set(key, "");
		}
		return true;
	}
	
	private String toMD5(String sourceString) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte []bs = sourceString.getBytes();
		byte[] digest = md5.digest(bs);
		return digest.toString();
	}


	public abstract void processHtml() throws InterruptedException, IOException, NoSuchAlgorithmException ;
	
}
