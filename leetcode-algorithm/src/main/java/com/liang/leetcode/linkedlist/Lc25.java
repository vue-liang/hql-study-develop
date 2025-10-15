package com.liang.leetcode.linkedlist;

// K个一组翻转链表
public class Lc25 {
    public static void main(String[] args) {
        ListNode listNode = reverseKGroup(ListNode.construct(2, 1, 4, 3, 5), 2);
        System.out.println(ListNode.toString(listNode));
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode t = head;
        for (int i = 0; i < k; i++) {
            if(t==null){
                return head;
            }
            t = t.next;
        }
        ListNode pre = reverseKGroup(t,k);
        ListNode p = head;
        ListNode temp;
        for (int i = 0; i < k; i++) {
            temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        return pre;
    }
}
