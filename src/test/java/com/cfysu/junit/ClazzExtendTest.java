package com.cfysu.junit;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.cfysu.model.Car;

public class ClazzExtendTest extends Car{

	ClazzExtendTest() {
		//super(1, "");
	}
	public void whateverMethod(){
		
	}
	String name = "小明";
	
	@Test
	public void testArray() {
		Car car = new ClazzExtendTest();
		ClazzExtendTest test = (ClazzExtendTest)car;
		test.whateverMethod();
		A a = new A();
		a.age = 12;
		a.name = "aaa";
		B b = new B();
		try {
			BeanUtils.copyProperties(a, b);
		} catch (Exception e) {
		}
		System.out.println("res:"  + JSON.toJSONString(b));
	}
}

class A{
	public int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String name;
}

class B{
	public int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}