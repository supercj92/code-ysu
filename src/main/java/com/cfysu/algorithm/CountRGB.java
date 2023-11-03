package com.cfysu.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author canglong
 * @Date 2023/11/3
 */
public class CountRGB {
    public int countPoints(String rings) {
        Map<Character, Set<Character>> position2StatusMap = new HashMap<>();
        char[] chars = rings.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                //偶数表示颜色
                ensurePosition(position2StatusMap, chars[i + 1]);
                position2StatusMap.get(chars[i + 1]).add(chars[i]);
            } else {
                //奇数表示位置
                //ensurePosition(position2StatusMap, chars[i]);
            }
        }
        long count = position2StatusMap.entrySet().stream().filter(entry -> entry.getValue().size() >= 3).count();
        return (int)count;
    }

    private void ensurePosition(Map<Character, Set<Character>> position2StatusMap, char item) {
        Character key = item;
        if (!position2StatusMap.containsKey(key)) {
            position2StatusMap.put(key, new HashSet<>());
        }
    }
}
