package com.liang.leetcode.doublepointer;

// 42. 接雨水
public class Lc42 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height1 = {4, 2, 0, 3, 2, 5};
//        System.out.println(trap(height));
//        System.out.println(trap(height1));
        System.out.println(trap1(height));
        System.out.println(trap1(height1));
        System.out.println(trap2(height));
        System.out.println(trap2(height1));
    }

    private static int trap(int[] height) {
        int max = 0;
        int sum = 0;
        for (int j : height) {
            if (j > max)
                max = j;
            sum += j;
        }
        int count = 0;
        int l, r;
        for (int i = 1; i <= max; i++) {
            l = 0;
            r = height.length - 1;
            while (l < r && height[l] < i) l++;
            while (l < r && height[r] < i) r--;
            count += r - l + 1;
        }
        return count - sum;
    }

    private static int trap1(int[] height) {
        int n = height.length;
        int[] pre_max = new int[n];
        int[] suf_max = new int[n];
        pre_max[0] = height[0];
        suf_max[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            pre_max[i] = Math.max(height[i], pre_max[i - 1]);
            suf_max[n - i - 1] = Math.max(height[n - i - 1], suf_max[n - i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(pre_max[i], suf_max[i]) - height[i];
        }
        return ans;
    }

    private static int trap2(int[] height) {
        int n = height.length;
        int pre_max = 0;
        int suf_max = 0;
        int left = 0;
        int right = n - 1;
        int ans = 0;
        while (left < right) {
            pre_max = Math.max(pre_max, height[left]);
            suf_max = Math.max(suf_max, height[right]);
            ans += pre_max > suf_max ?
                    suf_max - height[right--] :
                    pre_max - height[left++];
        }
        return ans;
    }

}
