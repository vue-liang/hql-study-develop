package com.liang.leetcode.array;

import java.util.*;

/**
 * @author liang
 * @create 2025-12-15 10:58
 * @description 56. 合并区间
 */
public class Lc56 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merge = merge(intervals);
        for (int[] m : merge) {
            System.out.print(Arrays.toString(m));
        }
    }

    public static int[][] merge(int[][] intervals) {
        // 1. 按照左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(p -> p[0]));
        List<int[]> ans = new ArrayList<>();
        // 2. 遍历
        for (int[] interval : intervals) {
            int m = ans.size();
            // 3. 如果 现在的左<上一个的右 则将上一个的右更新为 现在的右
            if (m > 0 && interval[0] <= ans.get(m - 1)[1]) {
                ans.get(m - 1)[1] = Math.max(interval[1], ans.get(m - 1)[1]);
            } else {
                // 4. 结果中无区间 或者 当前无相交
                ans.add(interval);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

}
