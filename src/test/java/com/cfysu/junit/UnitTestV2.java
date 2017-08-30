package com.cfysu.junit;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import com.cfysu.model.Ford;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UnitTestV2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnitTestV2.class);

	private Random random = new Random();
	@Test
	public void testConstruct(){
		new Ford();
	}

	@Test
	public void testDivid(){
		System.out.println(10/3);
	}

	@Test
	public void testVerificationCode(){
		int verficationCode = random.nextInt(9999) % (9999-1000+1) + 1000;
		System.out.print(verficationCode);
	}

	@Test
	public void testGetName(){
		System.out.print(UnitTest.class.getName());
		ClassLoader clazz = UnitTest.class.getClassLoader();
	}

	@Test
	public void testHashMap(){
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("qqq" ,"www");
		addHashMap(hashMap);

		//这时候qqq对应的value是多少？
		System.out.print(JSON.toJSONString(hashMap));
	}

	private void addHashMap(Map<String, String> hashMap){
		hashMap = new HashMap<String, String>();
		hashMap.put("qqq", "ttt");
	}

	@Test
	public void testCalendar(){
		Calendar calendar = Calendar.getInstance();
		System.out.print(calendar.get(Calendar.DAY_OF_MONTH));
		}


	@Test
	public void testLoggerInfo(){
		LOGGER.info("arg1:{}, arg2:{}, arg3:{}",1,2,3);
		String[] args = {"jack", "rose", "tom"};
		LOGGER.warn("第一名{},第二名{}，第三名{}", args);
	}


	}


