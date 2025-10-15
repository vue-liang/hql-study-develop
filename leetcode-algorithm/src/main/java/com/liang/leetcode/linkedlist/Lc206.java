package com.liang.leetcode.linkedlist;

// 反转链表
public class Lc206 {

    public static void main(String[] args) {
        ListNode listNode = reverseList(ListNode.construct(1, 2, 3, 4, 5));
        System.out.println(ListNode.toString(listNode));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        ListNode temp;
        while(p!=null){
            temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        return pre;
    }
}
