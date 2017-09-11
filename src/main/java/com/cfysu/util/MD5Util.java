package com.cfysu.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cj on 2017/9/11.
 */
public class MD5Util {

    @Test
    public void testMd5() {
        System.out.println(md5("helloworld"));
    }

    public String md5(String string) {
        String encodeStr = null;
        try {
            encodeStr = new String(MessageDigest.getInstance("md5").digest(string.getBytes("utf-8")), "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    @Test
    public void testBinary() {
        int num = 10;
        //00001010 1*2*2*2 + 0*2*2 + 1*2 + 0*1
        printInBinaryFormat(num);

        //无符号右移1位
        //00000101
        printInBinaryFormat(num >>> 1);

        //右移1位
        //00000101
        printInBinaryFormat(num >> 1);

        //左移1位
        //00010100
        printInBinaryFormat(num << 1);

        //同为1，则为1
        //00001010
        //00001111
        //00001010
        printInBinaryFormat(num & 15);

        //只要有一个为1，则为1
        //00001010
        //00001111
        //00001111
        printInBinaryFormat(num | 15);

    }

    @Test
    public void testHex () throws Exception{
        char a = 'a';
        char A = 'A';
        System.out.println((int)A);

        System.out.println(toHexStr("hello".getBytes()));

        System.out.println("----byte[]---");
        byte[] bytes = "汉字".getBytes("UTF-8");
        for(int i = 0;i < bytes.length;i++){
           System.out.println((int) bytes[i]);
        }
    }

    @Test
    public void testString2Binary(){
        printBinary("h");
    }

    public void printInBinaryFormat(int num) {
        System.out.println(Integer.toBinaryString(num));
    }

    /**
     * 将字节数组转成16进制字符串
     * @param bytes
     * @return
     */
    public String toHexStr(byte[] bytes) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bytes.length; ++i) {
            sb.append("0123456789abcdef".charAt(bytes[i] >>> 4 & 15));
            sb.append("0123456789abcdef".charAt(bytes[i] & 15));
        }

        return sb.toString();
    }

    /**
     * 将字符串转成二进制
     * @param string
     */
    public void printBinary(String string){
        char[] chars = string.toCharArray();
        for (int i = 0;i < chars.length;i++){
            //System.out.println((int)chars[i]);
            System.out.print(Integer.toBinaryString(chars[i]) + " ");
        }
    }

}
