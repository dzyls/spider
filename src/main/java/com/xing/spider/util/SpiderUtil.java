package com.xing.spider.util;

import java.util.Random;

public class SpiderUtil {
	
	
	public static void sleep() throws InterruptedException {
		sleep(randomSleepTime());
	}
	
	public static void sleep(int sleepTime) throws InterruptedException {
		Thread.sleep(sleepTime);
	}
	
	public static int randomSleepTime() {
		return new Random().nextInt(4500)+500;
	}
	

}
