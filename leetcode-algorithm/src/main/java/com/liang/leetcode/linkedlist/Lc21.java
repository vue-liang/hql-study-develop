package com.liang.leetcode.linkedlist;

// 合并两个有序链表
// TODO 典型递归
public class Lc21 {
    public static void main(String[] args) {
        ListNode list1 = ListNode.construct(1, 2, 4);
        ListNode list2 = ListNode.construct(1, 3, 4);
        Lc21 lc21 = new Lc21();
        System.out.println(ListNode.toString(lc21.mergeTwoLists(list1, list2)));
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
