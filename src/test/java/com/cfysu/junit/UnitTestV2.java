package com.cfysu.junit;

import com.alibaba.fastjson.JSON;
import com.cfysu.enums.MsgTypeEnum;
import com.cfysu.model.Car;
import com.cfysu.model.CoverAreaVo;
import com.cfysu.model.PlaceDto;
import com.google.common.collect.Lists;
import org.junit.Test;

import com.cfysu.model.Ford;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;

public class UnitTestV2 {

	static {

	}
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

	@Test
    public void testEnums(){
        System.out.println(MsgTypeEnum.MSG_TYPE_SUCCEED.getValue());
    }

    @Test
	public void testAddress(){
        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
	public void genTestData(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
	}

	@Test
	public void testPlaceDto(){

		List<PlaceDto> proviceList = new ArrayList<PlaceDto>();

		PlaceDto province1 = new PlaceDto();
		province1.setId("1");
		province1.setName("河南省");

		PlaceDto province2 = new PlaceDto();
		province2.setId("2");
		province2.setName("湖北");

		PlaceDto city1 = new PlaceDto();
		city1.setId("101");
		city1.setName("A市");

		PlaceDto city2 = new PlaceDto();
		city2.setId("102");
		city2.setName("B市");

		province1.getSubPlaceList().add(city1);
		province1.getSubPlaceList().add(city2);

		province2.getSubPlaceList().add(city1);
		province2.getSubPlaceList().add(city2);

		proviceList.add(province1);
		proviceList.add(province2);

		System.out.println(JSON.toJSONString(proviceList));
	}

	@Test
	public void testResolve(){
		String jsonStr = "[{\"id\":\"1\",\"name\":\"河南省\",\"subPlaceList\":[{\"id\":\"2\",\"name\":\"商丘市\",\"subPlaceList\":[]}]}]";

		List<PlaceDto> placeDtoList = JSON.parseArray(jsonStr, PlaceDto.class);

		System.out.println("res:" + placeDtoList.get(0).getName());
	}

	@Test
	public void testJson(){
		List<CoverAreaVo> coverAreaVoList = new ArrayList<CoverAreaVo>();

		String[] cityIds1 = {"1","2","3"};
		String[] cityIds2 = {"4","5","6"};

		CoverAreaVo coverAreaVo1 = new CoverAreaVo();
		coverAreaVo1.setProvinceId("11");
		coverAreaVo1.setProvinceName("湖北");
		coverAreaVo1.setCityNames("武汉,黄石,黄冈");
		coverAreaVo1.setCityIds(cityIds1);

		CoverAreaVo coverAreaVo2 = new CoverAreaVo();
		coverAreaVo2.setProvinceId("22");
		coverAreaVo2.setProvinceName("湖南");
		coverAreaVo2.setCityNames("XX1市,XX2市,XX3市");
		coverAreaVo2.setCityIds(cityIds2);

		coverAreaVoList.add(coverAreaVo1);
		coverAreaVoList.add(coverAreaVo2);

		System.out.println(JSON.toJSONString(coverAreaVoList));
	}
	@Test
	public void testWriteSeri(){
		PlaceDto placeDto = new PlaceDto();
		placeDto.setName("wuhan");
		placeDto.setId("1");

		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("E:\\cj.txt"));
			objectOutputStream.writeObject(placeDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testReadSeri(){
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("E:\\cj.txt"));
			PlaceDto placeDto = (PlaceDto)objectInputStream.readObject();
			System.out.println(JSON.toJSONString(placeDto));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Test
	public void testFor(){
		List<PlaceDto> placeDtoList = new ArrayList<PlaceDto>();
		PlaceDto placeDto = new PlaceDto();
		placeDto.setId("1");
		placeDto.setName("one");
		PlaceDto placeDto2 = new PlaceDto();
		placeDto2.setId("2");
		placeDto2.setName("two");
		placeDtoList.add(placeDto);
		placeDtoList.add(placeDto2);

		for(PlaceDto placeDtoItem : placeDtoList){
			//placeDtoItem = null;
			placeDtoItem.setId("33");
		}
		System.out.println(JSON.toJSONString(placeDtoList));
	}

	@Test
	public void testSplit(){
		String shopIdStr = "155";
		String[] shopIdArray = shopIdStr.split(",");
		for(String shopId : shopIdArray){
			System.out.println("shopId:" + shopId);
		}
	}

	@Test
	public void testCount(){
		double outPut = 848.48 + 1236.18 + 1803.24 + 1676 + 1795.72;
		double inPut = 394.44 + 866.65 + 1490.71 + 1378.98;
		double[] parmArray = {outPut, inPut, (outPut - inPut)};
		LOGGER.warn("支出:{}，收入:{},净支出:{}", parmArray);
	}

	@Test
	public void testCharSize() throws UnsupportedEncodingException {
		String a = "a";

		char[] chars = a.toCharArray();

		System.out.println(chars.length);

		System.out.println("utf-16 size:" + a.getBytes("gbk").length);

		System.out.println("utf-8 size:" + a.getBytes("utf-8").length);
	}

	@Test
	public void testArrayList(){
		List<Integer> integerList = new ArrayList<>();
		integerList.add(1);
		System.out.println("intSize:" + integerList.size());
		System.out.println(new ArrayList<String>(100).size());
	}

	@Test
	public void testArrayList2(){
		List<String> strList = Lists.newArrayList();
		for(int i = 0;i < 10;i++){
			strList.add(Integer.toString(i));
		}
		Iterator<String> iterator = strList.iterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			if(next.equals("5")){
				//抛出ConcurrentModificationException
				//strList.remove("5");
				iterator.remove();
			}
		}
		System.out.println(JSON.toJSONString(strList));
	}

	@Test
    public void testRandom(){
		//如果种子相同，会产生相同的伪随机数
        System.out.println(new Random(50).nextInt(10));
		System.out.println(new Random(50).nextInt(10));
    }

    @Test
	public void testRuntime() throws IOException {
    	Runtime runtime = Runtime.getRuntime();
    	//调用系统命令
    	runtime.exec("calc.exe");
    	runtime.exec("notepad.exe");
	}

	@Test
	public void testGeneric(){
    	//ArrayList<String>()并不是List<Object>的子类
    	//List<Object> objectList = new ArrayList<String>();
		List list = new ArrayList();
		List arrayList = new ArrayList<String>();

		Car[] cars = new Car[2];
		Ford[] fords = new Ford[2];

		//fail at runtime
		Object[] objects = new Integer[2];
		objects[0] = "word";
	}

}


