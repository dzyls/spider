package com.xing.spider.MySpider;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DoubanSpider {

	public static void main(String[] args) throws IOException {
		
		Connection connect = Jsoup.connect("https://www.douban.com/");
		Connection connection = connect.header("Cookie", "ll=\"118237\"; bid=_M8TPT3n6oI; _vwo_uuid_v2=D998F04B74822F1D62175F6A21F52C9B|b288cdb9855c5bcda0a75df8d8d94af1; viewed=\"26857423\"; gr_user_id=969e1077-896d-4f6d-86a6-c6c5f1bcb297; ap=1; __yadk_uid=rjWXJR8Qv02nWzXza15fAz9Uf7SbS2jt; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1514531756%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3Dt3Ev7-cb_9E2HxGoqgZCjC4Kdi2okyoK7zbZup-BYKy%26wd%3D%26eqid%3Db7426a4b000656aa000000035a45eba7%22%5D; _pk_ses.100001.8cb4=*; __utma=30149280.448531864.1510909074.1514364763.1514531758.4; __utmc=30149280; __utmz=30149280.1514531758.4.4.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; ps=y; ue=\"1217167420@qq.com\"; dbcl2=\"134821992:7hKv21Tsha0\"; ck=ywB1; push_noty_num=0; push_doumail_num=0; __utmv=30149280.13482; __utmt_douban=1; gr_session_id_22c937bbd8ebd703f2d8e9445f7dfd03=4949fe25-8522-47c6-871f-5d49065d8acb; gr_cs1_4949fe25-8522-47c6-871f-5d49065d8acb=user_id%3A1; __utmt=1; _pk_id.100001.8cb4=ed2a4600b0fb5a70.1514364751.2.1514532486.1514364751.; __utmb=30149280.16.10.1514531758");
		connection.header("Host", "www.douban.com");
		connect.header("Referer", "https://www.douban.com/people/dzyls/");
		connect.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
		Document document = connect.get();
		System.out.println(document);
		
		
		
		
		
		
		
	}
	
}
