package com.cfysu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author canglong
 * @Date 2021/11/17
 */
public class RegExpMain {

    public static final Pattern SPACIAL_IMG_PATTERN = Pattern.compile("(?<one>ht|f)tp(s?)://[\\S]+\\.(jpg|png|bmp)");

    public static void main(String[] args) {
        String content
            = "https://img.alicdn.com/imgextra/i2/O1CN01kcBcHM1qtK3gE692s_!!0-amp.jpg      https://item.taobao"
            + ".com/item.htm?id=557600833378&scm=20140619.rec.2107759029.557600833378";
        Matcher matcher = SPACIAL_IMG_PATTERN.matcher(content);
        System.out.println(matcher.find());
        System.out.println(matcher.group());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        //可以为捕获组指定别名，格式<?name>
        System.out.println(matcher.group("one"));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));

    }
}
