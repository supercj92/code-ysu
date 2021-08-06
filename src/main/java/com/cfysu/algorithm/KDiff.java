package com.cfysu.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author canglong
 * @Date 2020/4/23
 */
public class KDiff {

    public static void main(String[] args) {
        //演示comparable
        List<Diff> diffList = new ArrayList<>();
        for(int i = 0; i < 10;i++){
            Diff diff = new Diff(new Random().nextInt(10), 0, 0);
            diffList.add(diff);
        }
        System.out.println(diffList);
        Collections.sort(diffList);
        System.out.println(diffList);
        //KDiff kDiff = new KDiff();
        //List<Integer> candidate = Arrays.asList(3, 1, 4 ,1 ,5);
        //
        //Set<Diff> diffs = kDiff.calculateKDiffCount(candidate, 2);
        //System.out.println(diffs.size() + " :filtered set:" + JSONObject.toJSONString(diffs));
    }

    public Set<Diff> calculateKDiffCount(List<Integer> candidate, int diff){
        Set<Diff> permutation = new HashSet<>();
        List<Diff> permutationList = new ArrayList<>();
        List<Diff> duplicate = new ArrayList<>();
        for(Integer subtractor : candidate){
            for(Integer minuend : candidate){
                Diff diffItem = new Diff(subtractor - minuend, subtractor, minuend);
                boolean add = permutation.add(diffItem);
                if(!add){
                    duplicate.add(diffItem);
                }
                permutationList.add(diffItem);
            }
        }
        System.out.println(permutationList.size() + " :permutationList:" + JSONObject.toJSONString(permutationList));
        System.out.println(duplicate.size() + " :duplicate: " + JSONObject.toJSONString(duplicate));
        System.out.println(permutation.size() + " :permutationSet:" + JSONObject.toJSONString(permutation));
        permutation = permutation.stream().filter(diff1 -> diff1.getDiff() == diff).collect(Collectors.toSet());
        return permutation;
    }

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    static class Diff implements Comparable<Diff>{
        int diff;
        int subtractor;
        int minuend;

        @Override
        public int hashCode() {
            return diff;
        }

        @Override
        public boolean equals(Object object) {
            Diff diff = (Diff)object;
           return diff.getDiff() == this.getDiff() && diff.getSubtractor() == this.subtractor && diff.getMinuend() == this.minuend;
        }

        @Override
        public int compareTo(Diff o) {
            return Integer.compare(this.diff, o.diff);
        }
    }
}
