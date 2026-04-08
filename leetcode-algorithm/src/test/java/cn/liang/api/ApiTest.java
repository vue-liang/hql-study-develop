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
        List<Long> nums = new ArrayList<>(List.of(6L, -1L, 7L, 4L, 2L, 3L));
        nums.sort((a, b) -> (int) (b - a));
        System.out.println("测试排序："+ nums);
    }

}
