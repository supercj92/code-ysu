package com.cfysu.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @Author canglong
 * @Date 2023/3/5
 * 解析命令行参数
 * 系统依赖A服务，但是该服务存在稳定性问题，rt和失败率比较高。为了保证系统的稳定性，你需要设计一个断路器。保证当A服务成功率、平均rt超过阈值时，停止对A服务的调用，以保证当前系统的稳定性。
 * 要求：要考虑到流控规则的扩展性
 *
 *
 *
 * 设计一个命令行程序参数的解析方法，将用户的输入的参数解析为键值对的形式。
 * 例如、用户原始输入为"       -a=123 -b='456 789' --c='a+2'"，解析后的结果为"-a=123","-b='456 789'","--c='a+2'"。
 * 要求：
 * 1、不同参数之间的分隔符是可变的
 * 2、不可使用java.lang.String.split(java.lang.String)方法
 *
 *
 *
 * 实现一个方法可以对给定的链表去重。
 * 例如、给定链表为1,2,2,4,2，去重后链表为1,2,4
 * 要求：要考虑到空间复杂度和时间复杂度
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
