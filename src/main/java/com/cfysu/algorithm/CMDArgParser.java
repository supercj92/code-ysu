package com.cfysu.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/3/5
 * 解析命令行参数
 */
public class CMDArgParser {

    @Test
    public void testSplit() {
        List<String> parse = parse("a=123 b='456 789' --c='a+2'", ' ');
        List<String> expected = new ArrayList<>();
        expected.add("a=123");
        expected.add("b='456 789'");
        expected.add("--c='a+2'");
        Assertions.assertThat(parse).isEqualTo(expected);

        List<String> parse2 = parse("abcd1efgh", '1');
        List<String> expected2 = new ArrayList<>();
        expected2.add("abcd");
        expected2.add("efgh");
        Assertions.assertThat(parse2).isEqualTo(expected2);

        //List<String> parse3 = parse("abcd11efgh", '1');
        //List<String> expected3 = new ArrayList<>();
        //expected3.add("abcd");
        //expected3.add("efgh");
        //Assertions.assertThat(parse3).isEqualTo(expected3);
    }

    //a=123 b='456 789' --c='a+2'
    public List<String> parse(String input, char splitter) {
        char[] chars = input.toCharArray();
        int startIndex = 0;
        List<String> result = new ArrayList<>();
        boolean inArgs = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\'') {
                inArgs = !inArgs;
            }
            boolean lastChar = (i == chars.length - 1);
            if (((chars[i] == splitter) || lastChar) && !inArgs) {
                int endIndex = (lastChar ? i + 1 : i);
                result.add(input.substring(startIndex, endIndex));
                startIndex = i + 1;
            }
        }
        return result;
    }
}
