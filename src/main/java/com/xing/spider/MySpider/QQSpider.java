package com.xing.spider.MySpider;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class QQSpider {

	public static void main(String[] args) throws IOException {
		
		Connection connect = Jsoup.connect("https://user.qzone.qq.com/1217167420");
		Connection connection = connect.header("Cookie", "eas_sid=s11511d024W5L999z6q272d5a9; LW_uid=i15581X0Z4h8f1t28587n4g3j5; pgv_pvi=1081633792; RK=wvGyU84qNO; tvfe_boss_uuid=a4de3ccbc5f0bc6b; ue_ts=1510973766; ue_uk=7bfdf481f275f25663142cefd5b89f62; ue_uid=73b1e90a917a726f8b74ef2b1cf4af39; ue_skey=864042cb486d51bdba01b7f6369ddb92; LW_sid=D1o5a1a0H9j7o3P7g6p9Y4A5k4; LW_TS=1510973766; LW_PsKey=a72462799d85d6ed23cfc53acf501304; LW_pid=ecc91585a570367ffdc14bd6bd537934; pac_uid=0_6b9a6c91eba79; gaduid=5a28ee10e243c; pgv_pvid_new=715145306_4d57cec293; ptui_loginuin=1217167420; ptcz=dec1d5b7b29672550fefd22183a4fe65ba69fe093eab96136d62ed86a2e6fb93; o_cookie=1217167420; pgv_pvid=2701554145; pt2gguin=o1217167420; qz_screen=1920x1080; QZ_FE_WEBP_SUPPORT=1; cpu_performance_v8=0; luin=o1217167420; lskey=000100001d89378560630435b1225e1fac6269213ffd930784563fa565f54f37a75fc69598ba03a281732efe; pgv_info=ssid=s4371690492; pgv_si=s8032228352; _qpsvr_localtk=0.12669802927948792; ptisp=cm; uin=o1217167420; skey=@NLmFQDSAq; p_uin=o1217167420; pt4_token=z-lArf9cHq9uw4yBZrV3ChMC1jAbE94hGdNb4rGxr*s_; p_skey=5Nlk5eHGP-7QpKbL9-DrVwZvLX8K8Io2mRVu3few92s_; fnc=2; Loading=Yes; 1217167420_todaycount=0; 1217167420_totalcount=5840");
		//connection.header("Host", "www.douban.com");
		connect.header("Referer", "https://qzs.qq.com/qzone/v5/loginsucc.html?para=izone");
		connect.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
		Document document = connect.get();
		write2Text(document);
	}
	
	public static void write2Text(Document document) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("qZone.html"));
		writer.write(document.html());
		writer.close();
		
		
	}
	
	
}
