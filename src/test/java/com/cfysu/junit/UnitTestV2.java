package com.cfysu.junit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.PatternLayout;
//import ch.qos.logback.core.pattern.Converter;
//import ch.qos.logback.core.pattern.parser.Node;
//import ch.qos.logback.core.pattern.parser.Parser;
//import ch.qos.logback.core.spi.ScanException;
import com.cfysu.enums.MsgTypeEnum;
import com.cfysu.model.Car;
import com.cfysu.model.CoverAreaVo;
import com.cfysu.model.PlaceDto;
import com.cfysu.pattern.builder.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.cfysu.model.Ford;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

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
		String shopIdStr = "a";
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

	@Test
	@SuppressWarnings("static-access")
	public void testNull(){
		//null可以强制类型转换
		String str = (String)null;
		System.out.println(str);

		//可以调用静态方法
		((UnitTestV2)null).handler1();

		//不可以调用实例方法
		((UnitTestV2)null).handler2();
	}

	public static void handler1(){
		System.out.println("handler1");
	}

	public void handler2(){
		System.out.println("handler2");
	}

	@Test
	public void testttt(){
		boolean res = (1==1);
		System.out.println(res);
	}

	@Test
	public void testClassloader(){
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(contextClassLoader.getParent());
		System.out.println(contextClassLoader.getParent().getParent());
		//System.out.println(contextClassLoader.getParent().getParent().getParent().getParent());
	}

	@Test
	public void testForeach(){
		List<String> strList = new ArrayList<>(2);
		strList.add("1");
		strList.add("2");
		// 抛出ConcurrentModificationException
		// 如果是"1".equals(str)的话就不会抛异常
		for(String str : strList){
			if("2".equals(str)){
				strList.remove(str);
			}
		}
	}

	@Test
	public void testSplit2(){
		String storeHeadImgUrl = "//bbcgw.m.jd.com/bbc.cStoreImgV2?applyId=900141&width=15&height=1.00&shopName=111&shopNo=NB999400";
		String type = null;
		if(storeHeadImgUrl.contains("type")){
			String[] items = storeHeadImgUrl.split("&");
			for(String item : items){
				if(item.contains("type")){
					String[] keyAndValue = item.split("=");
					if(keyAndValue.length == 2){
						type = keyAndValue[1];
					}
				}
			}
		}
		System.out.println(type);
	}

	@Test
	public void testXStream() throws FileNotFoundException {
		XStream xStream = new XStream(new DomDriver());
		User user = new User.UserBuilder().userName("cj").pwd("pwd").build();
		xStream.toXML(user, new OutputStreamWriter(new FileOutputStream("D:\\user.xml")));
	}

	@Test
	public void testMapObj(){
		Map<String, Car> name2CarMap = Maps.newHashMap();
		name2CarMap.put("ford", new Car(4, "ford"));
		Car fordCar = name2CarMap.get("ford");
		fordCar.setBrand("audi");

		System.out.println(name2CarMap.get("ford").getBrand());
	}

	@Test
	public void testMapGet(){
		Map parmMap = Maps.newHashMap();
		System.out.println(parmMap.get("key") == null);
	}

	@Test
    public void testAppendNull(){
        System.out.println("dd" + null);
    }


    @Test
	public void testMap(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("mirobot", "starbucks_retail");
		map.put("retail", "starbucks_retail");
		System.out.println(JSONObject.toJSONString(map));
	}

	@Test
	public void testParseLong(){
		Long.valueOf("580695008692580695008692");
	}

	@Test
	public void testChar(){
		System.out.println('1' == 49);
		System.out.println(Integer.toBinaryString(49));
	}

	@Test
	public void testObj(){
		System.out.println(JSONObject.toJSONString(new TestObj("1", "2")));
		System.out.println(JSON.toJSONString(new TestObj("1", "2")));
	}

	class TestObj{
		private String param1;
		private String param2;
		public TestObj(String str1, String str2){
			this.param1 = str1;
			this.param2 = str2;
		}
		public String getParam1(){
			return param1;
		}
	}

	@Test
    public void testPrint(){
	    List<String> strList = Arrays.asList("str1", "str2", "str3");
        System.out.println(strList);

        strList.forEach(System.out::println);
    }

    @Test
	public void testOptional(){
		Optional<Object> empty = Optional.empty();
		System.out.println(empty.isPresent());
	}

	@Test
    public void testValue(){
	    Car car = new Car();
	    car.setBrand("ford");
	    car.setCar(new Car(3, "shifeng"));
	    //change(car);
		System.out.println(car.toString());

    }

    //@Test
	//public void testLogbackParser() throws ScanException {
	//	//Parser parser = new Parser("%d{yyyy-MM-dd HH:mm:ss.SSS} [%X{traceId}] [%t] %-5p %c{2} - %m %n");
	//	//parser.setContext(new LoggerContext());
	//	//Node head = parser.parse();
	//	//Converter converter = parser.compile(head, PatternLayout.defaultConverterMap);
	//	//String convert = converter.convert(null);
	//	//System.out.println(parser);
	//}

    @Test
	public void testCompare(){
		String one = "abcxxx";
		String two = "abdxxx";
		String three = "bxxxxx";
		System.out.println(one.compareTo(two));
		System.out.println(one.compareTo(three));
	}

	@Test
	public void testJoin(){
		String topPV = "1100000394658,1714128138,2616970884,1917047079,2928278102,2549841410,2360209412,143584903,"
			+ "890482188,92688455,533497499,2275046294,385132127,749391658,446338500,420722466,667286523,1049653664,"
			+ "520557274,196993935,2838892713,835893472,925814798,3164711246,353571709,858704520,352469034,676606897,"
			+ "740469814,430490406,685563493,349740505,2830036684,1100002958409,379424083,363607599,2129855716,"
			+ "642320867,1703307078,859515618,1664945146,458694874,2528254892,698461607,2089100916,397341302,"
			+ "2259702327,925772082,3527212490,901409638,2738112600,3392536705,648476316,669642169,138006397,"
			+ "217101303,133668489,3369266294,3035493001,700459267,387542955,1100000113027,740958699,859230932,"
			+ "407910984,734584252,525910381,1100003649176,1100000106088,2973966816,1100004183311,1744921231,"
			+ "1100004853223,1100000059038,173275708,1638292152,628189716,160586276,2271279753,1741393998,"
			+ "1100000058114,1100003930370,897609396,1100010612021,1100003820863,3079263591,1100007300010,898146183,"
			+ "730053232,20003478,1704328704,746866993,1100003431096,3015214310,2978398582,1114511827,2217928793,"
			+ "167873659,3256243055";

		String topSolution = "101450072,117202952,138960927,263817957,277249531,277627577,290788550,345921674,355460153,389335512,405312179,452325706,688024484,750636142,762526146,898332654,1579139371,1646313508,1983407859,2002005727,2023195423,2091012777,2129034002,2129855716,2279845441,2352952336,2377348361,2380958892,2454121079,2658963920,2928278102,3159735360,3161393975,1100000041255,1100000624156,1100000781606,1100000784384,1100001335190,1100002644171,1100003578517,1100004233440,1100004493426,1100005092028,1100007777092,1100008600010,1100009796260,1100010516348,94504595,201749140,363607599,395601843,628189716,738698375,745475881,820521956,849090736,1105025069,1652714638,1710482305,1721181839,1748416497,1818112088,1983806421,2005831794,2058279615,2143008983,2176936131,2576991140,2579408925,2635083319,2735822823,2794371653,2894753180,3293619053,3402980386,1100000073281,1100000211656,1100000982799,1100007300084,1100007671013,1100007924509,1100009779358,1100009785558,106852162,109955314,160586276,217101303,268691146,269169475,272205633,500327991,667690233,776109305,839895996,925772082,1023696028,1588885624,1714128138,2112191563,2566110185,2649573952,2691044240,2883596855,2979107860,1100000078294,1100000202157,1100001047547,1100004152595,1100004579301,1100007721277,1100008178660,151940621,352469034,372477188,648580690,685563493,702611475,858915326,910933581,1573475524,1644123097,1732515655,1819441444,2074722020,2139295513,2157661959,2203460094,2275046294,2319077055,2549841410,2641462734,2810669937,2939922051,3165589196,3175325124,3242164590,3564588070,1100000394658,1100000944720,1100000982370,1100001041426,1100002460765,1100003430373,1100003431096,1100007925065,2243799558,2452498434,73861778,92042735,134363478,240610147,397341302,430490406,642320867,682114580,704862361,706084574,713464357,738722023,765844183,765922982,783329018,797517839,880734502,916785489,1102774009,1652553642,1783896189,1975328959,2087785066,2226241138,2329932483,2344126724,2452359127,2608180479,2615125783,2668714578,2677542111,2830036684,2854787224,2883870050,2916494043,2978398582,3166931255,3626417285,1100000014039,1100003437075,1100004049869,1100004130418,1100006112016,1100008064884,30452024,92592768,150126974,366168649,413996455,478829986,504729396,515369883,700459267,711882582,712310323,745949152,756162799,902929889,1026430696,1650429497,1652554937,1653885972,1753075900,1986899349,1987669206,2616970884,2670334332,2932255676,1100000501707,1100002423522,1100004816156,1100007635114,1100008331978,1100010396675,18139021,20003478,143584903,179917267,184783763,279951700,321036329,379424083,383602586,444076877,514175954,641725918,647599026,665862336,725677994,746408297,807691208,1016410163,1879183449,2027551265,2452744829,2455135876,2468809135,2660257049,2707252427,2807304908,2830087192,2836003954,3015214310,3164711246,3188449667,3475858679,1100000070211,1100000906169,1100001373354,1100003107141,1100003115259,1100005224087,1100008024439,1100008560828,1100009726031,50454948,353050747,467221028,470168984,613968296,705657896,731584844,749901026,845001562,1650269249,1666038058,1741393998,2037432060,2098914042,2259702327,2421494392,2454990786,2455124912,2594916711,2824382374,2973966816,3089403217,3405569954,3586957598,3644770036,1100000155595,1100000634110,1100000757166,1100000999032,1100002446656,1100003660107,1100007857121,16343550,52467668,133668489,617932342,688017899,693670519,732501769,1615368232,1940724523,2071216161,2097385502,2219724804,2526051849,2880567392,1100000176012,33198661,379250310,669642169,1044787662,1664945146,2032840798,2100936062,3257577508,1100000598729,1100002964196,1100008514025,138468814,786798443,1122313708,2295782084,2367646892,2668422887,2996363856,3560042407,1100003500005,1100005233214,1100007824129,92688455,356579667,704407379,775022306,916243692,925649214,1610161411,1621277539,1910887896,2031615721,2065786850,2219509495,2754622571,3097858254,3164693811,1100000203164,1100000874989,1100001276183,1100001354835,1100007749126,94399436,368609005,389336434,486093582,516548726,749391658,1040431341,2389794079,2500428739,2796767321,3217473181,3375170974,3386984799,1100000070463,1100000180152,1100000476218,1100000641027,1100001035507,293015773,704631625,766568254,871826698,1024740735,1032435764,1601196901,1744921231,2872009706,2874613469,2935009234,3099613854,1100003510392,1100004669099,1100004760155,1100007805151,132989916,770082921,826019124,829250210,909438520,925111469,1124587100,2048258955,2231366563,2375690050,2682696826,2758662450,2766091417,3326294786,3409867774";

		Set<Long> topPVSet = toSet(topPV);
		Set<Long> topSolutionSet = toSet(topSolution);

		boolean b = topPVSet.retainAll(topSolutionSet);
		System.out.println(topPVSet);
		System.out.println(topPVSet.size());
	}

	private Set<Long> toSet(String string){
		String[] top100 = StringUtils.split(string, ",");
		return Arrays.stream(top100).map(Long::valueOf).collect(Collectors.toSet());
	}

    private void change(Car car){
	    car = new Car();
    }


}


