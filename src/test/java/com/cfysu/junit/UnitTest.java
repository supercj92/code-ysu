package com.cfysu.junit;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.cfysu.model.Car;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.hessian.service.HelloService;

public class UnitTest {

	Logger logger = Logger.getLogger(UnitTest.class);
	@Test
	public void testString(){
		//System.out.println("hello test");
		String oldUrl = "http://d6.yihaodianimg.com/TEST/M08/02/F9/CqGQPlPZ_1-AfnCtAADEmJPfnpM37500.webp";
		String size = "_200x200";
		String newUrl = null;
		if(oldUrl != null){
        	String picType = oldUrl.substring(oldUrl.lastIndexOf("."));
        	if(picType.length() > 1){
        		newUrl = oldUrl.replace(picType, size + picType);
        	}
        }
		System.out.println("oldUrl:" + oldUrl + "/brnewUrl:" + newUrl);
	}
	
	@Test
	public void testSplit(){
		String str = "http://shop.yhd.com/kpi/58375tp=16.72038_0.387.1_104.5.LZYCUiw-10-CpPcc&ti=9NF2AJ";
		String[] array = str.split("\\?");
		for(String item : array){
			System.out.println(item);
		}
	}
	
	@Test
	public void testReadFromConsole(){
		/*String prompt = "tips";
		String inStr = JOptionPane.showInputDialog(prompt);*/
		/*Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		System.out.println("input:" + str);
		*/
		StringBuffer sb = new StringBuffer();
		
		sb.append(System.getProperty("java.version"));
		sb.append(System.getProperty("os.name"));
		System.out.println("res:" + sb.toString());
	}
	
	
	@Test
	public void testUUID(){
		UUID uuid = UUID.randomUUID();
/*		System.out.println("uuid:" + uuid);
		Car myCar = new Car();
		myCar.wheelNum = 1;*/
	}
	
	@Test
	public void testLogger(){
		/*logger.info("test logger info");
		logger.error("test logger error");*/
		logger.warn("test logger warn");
	}
	
	@Test
	public void testHtmlescape(){
		String string1 = "刘&#39;白鸭&#39;呵呵&quot;";
		String string2 = "六百元'";
		HtmlUtils.htmlEscape(string2);
		HtmlUtils.htmlUnescape(string1);
		HtmlUtils.htmlEscape(string2);
		//HtmlUtils.html
		System.out.println("res:" + string2);
	}
	
	@Test
	public void testFor(){
		List<Long> list = null;
		for(Long itemLong : list){
			System.out.println("sdfsdfasd");
		}
		/*List<Car> testList = new ArrayList<Car>();
		testList.add(new Car(4, "bmw"));
		testList.add(null);
		for(Car car : testList){
			System.out.println("car's wheelNum:" + car.wheelNum);
		}*/
	}
	
	@Test
	public void testMapEntry(){
		Map<Long, Long> map = Maps.newHashMap();
		map.put(null, null);
		for(Map.Entry<Long, Long> entry : map.entrySet()){
			if(entry == null){
				System.out.println("entry is null");
			}else {
				System.out.println("entry Is not null");
			}
			
			if(entry.getKey() == null){
				System.out.println("key is null");
			}else {
				System.out.println("key is not null");
			}
			
		}
	}
	
	@Test
	public void testListAdd(){
		Car car = null;
		List<Car> carList = Lists.newArrayList();
		for(int i=0;i<10;i++){
			car = new Car();
			car.setBrand("brand"+i);
			carList.add(car);
		}
		
		for(Car car2 : carList){
			System.out.println("brand->" + car2.getBrand());
		}
	}
	
	@Test
	public void testHessianClient(){
		String url = "http://localhost:8080/hessian";
		HessianProxyFactory factory = new HessianProxyFactory();
		HelloService helloService = null;
		try {
			 helloService = (HelloService) factory.create(HelloService.class, url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = helloService.sayHello("test msg");
		System.out.println("res:" + res);
	}
	
	@Test
	public void testMapJSON(){
		Map<String, Object> conditionMap = Maps.newHashMap();

		conditionMap.put("num", 1);
		conditionMap.put("sort", 3);
		conditionMap.put("sortDir", "desc");
		conditionMap.put("brandId", 101);
		conditionMap.put("provinceId", 1);
		conditionMap.put("clientType", "2");
		conditionMap.put("statusCode", 2);
        System.out.println(JSON.toJSONString(conditionMap));
	}
	
	@Test
	public void testIterator(){
		List<String> strList = new ArrayList<String>();
		strList.add("a");
		strList.add("b");
		strList.add("b");
		strList.add("a");
		Iterator<String> iterator = strList.iterator();
		while (iterator.hasNext()) {
			if(iterator.next().equals("b")){
				iterator.remove();
			}
			
		}
		System.out.println(JSON.toJSONString(strList));
	}
	
	@Test
	public void testRefer(){
		Car car = new Car();
		String brandName = car.getBrand();
		Map<String, String> map = car.getPropertyMap();
		Integer price = car.getPrice();
		price = 110;
		map.put("key-one", "value-one");
		map = new HashMap<String, String>();
		//brandName +=;
		System.out.println("字符串的值：" + car.getBrand());
		System.out.println("属性map：" + JSON.toJSONString(car.getPropertyMap()));
		System.out.println("price:" + car.getPrice());
	}
	@Test
	public void testString2(){
		/*String str = "aaa";
		System.out.println(str.toUpperCase());*/
		String[] arrayStrings = new String[5];
		System.out.println(arrayStrings[0]);
	}
	
	@Test
	public void testURLEncode(){
		String str = "我是一个字符串";
		try {
			String encodedStr = URLEncoder.encode(str, "utf-8");
			//System.out.println("编码后：" + encodedStr);
			System.out.println("解码后：" + URLDecoder.decode("%E6%88%91%E6%98%AF%E4%B8%80%E4%B8%AA%E5%AD%97%E7%AC%A6%E4%B8%B2%", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
