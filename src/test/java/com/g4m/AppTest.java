package com.g4m;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private RestTemplate restTemplate = new RestTemplate();
	
	
	@Test
	public void testHtml() throws ParseException {
//		ResponseEntity<String> forEntity = restTemplate.getForObject("https://www.baidu.com/", ResponseEntity.class);
//		String json = JSONObject.toJSONString(forEntity);
//		System.out.println(json);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long a = sdf.parse("2018-12-01 00:00:00").getTime()-1000*60*60*24;
		Date date = new Date(a);
		System.out.println(sdf.format(date));
		
		if(new Date().getTime()-sdf.parse("2018-12-18 00:00:00").getTime()<1000*60*60*24) {  //1000毫秒x60秒x60分钟x24小时
			System.out.println("aaaaaa");
		}else {
			System.out.println("bbbbb");
		}
		
	}
}


