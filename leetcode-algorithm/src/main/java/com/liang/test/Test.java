package com.liang.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 计算众数（出现次数最多）并求最小的
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        List<Integer> list = Arrays.asList(1,4,5,3,4,1,5,3,7);
        System.out.println(test.getMode(new ArrayList<>(list)));
    }
    public int getMode (ArrayList<Integer> a) {
        // write code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for(Integer i : a){
            map.put(i, map.getOrDefault(i, 0) + 1);
            max = Math.max(max, map.get(i));
        }
        for(Integer i : a){
            if(max==map.get(i)){
                list.add(i);
            }
        }
        return list.stream().mapToInt(i -> i).min().getAsInt();
    }
}
