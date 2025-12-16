package cn.liang.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liang
 * @create 2025-12-15 13:43
 * @description
 */
public class ApiTest {

    @Test
    void test01(){
        List<Integer> nums = new ArrayList<>(List.of(6, -1, 7, 4, 2, 3));
        Collections.sort(nums);
        System.out.println("测试排序："+ nums);
    }

}
