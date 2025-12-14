package com.liang.leetcode.array;

import java.util.Arrays;

public class MySort {
    public static void main(String[] args) {
        int[] arr = {5, 7, 6, 8, 9, 1, 2, 3, 4};
//        bubbleSort(arr);
//        insertSort(arr);
//        selectSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        quickSort1(arr, 0, arr.length - 1);
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // 冒泡排序 左>右 不断交换 最大值在最右边
    public static void bubbleSort(int[] arr) {
        for (int j = arr.length; j > 0; j--) {
            // 每一趟，最大值在最右边
            for (int i = 0; i < j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    // 插入排序 左<右 插入 找到插入位置 左边是已排好序的
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int t = arr[i + 1];
            int j = i;
            while (j >= 0) {
                if (t < arr[j]) { // 前面的都是已排序好的
                    arr[j + 1] = arr[j]; // 大的→移
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = t;
            System.out.println(Arrays.toString(arr));
        }
    }

    // 选择排序 找到最小的 放到最左边
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int mIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[mIndex] > arr[j]) {
                    mIndex = j;
                }
            }
            int t = arr[mIndex];
            arr[mIndex] = arr[i];
            arr[i] = t;
            System.out.println(Arrays.toString(arr));
        }
    }

    // 快速排序 双指针法，保证 左<key  右>key
    public static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int left = begin;
        int right = end;
        int key = begin;
        while (left < right) {
            while (arr[right] >= arr[key] && left < right) right--;
            while (arr[left] <= arr[key] && left < right) left++;
            swap(arr, left, right);
        }
        swap(arr, key, left);
        key = left;
        System.out.println(Arrays.toString(arr));
        quickSort(arr, begin, key - 1);
        quickSort(arr, key + 1, end);
    }

    // 快速排序 挖坑法
    public static void quickSort1(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int left = begin;
        int right = end;
        int key = arr[begin];
        while (left < right) {
            while (arr[right] >= key && left < right) right--;
            arr[left] = arr[right];
            while (arr[left] <= key && left < right) left++;
            arr[right] = arr[left];
        }
        arr[left] = key;
        quickSort1(arr, begin, left - 1);
        quickSort1(arr, left + 1, end);
    }

    // 快速排序
    public static void quickSort2(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int start = begin;
        int key = arr[end];
        for (int i = begin; i < end; i++) {
            if (arr[i] < key) {
                swap(arr, start, i);
                start++;
            }
        }
        swap(arr, start, end);
        quickSort2(arr, begin, start - 1);
        quickSort2(arr, start + 1, end);
    }

    private static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
}
