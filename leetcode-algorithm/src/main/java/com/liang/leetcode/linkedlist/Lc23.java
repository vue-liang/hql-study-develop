package com.liang.leetcode.linkedlist;

// 合并K个升序链表
public class Lc23 {
    public static void main(String[] args) {
        Lc23 lc23 = new Lc23();
        ListNode list1 = ListNode.construct(1, 4, 5);
        ListNode list2 = ListNode.construct(1, 3, 4);
        ListNode list3 = ListNode.construct(2, 6);
        ListNode[] lists = {list1, list2, list3};
        System.out.println(ListNode.toString(lc23.mergeKLists(lists)));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return split(lists, 0, lists.length - 1);
    }

    public ListNode split(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        int m = (i + j) >>> 1;
        ListNode left = split(lists, i, m);
        ListNode right = split(lists, m + 1, j);
        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }
}
