package com.cfysu.datastructure;

import org.junit.Test;

import com.cfysu.datastructure.Stack;
//import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by longcangjian on 2017/6/8.
 */
public class StackTest {
    @Test
    public void reverseWord(){
       System.out.println("input please:");
       String content = readStringFromConsole();
       Stack stack = new Stack(content.length());
       for(int i = 0;i < content.length();i++){
    	   stack.push(content.charAt(i));
       }
       System.out.println("reversed:");
       for(int j = 0;j < content.length();j++){
    	   System.out.print(stack.pop());
       }
    }

    private String readStringFromConsole(){
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String inputString = null;
        try {
            inputString = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputString;
    }

    public static void main(String[] args){
        //readStringFromConsole();
        System.out.println("hello world");
    }
}
