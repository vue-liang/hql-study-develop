package com.liang.leetcode.linkedlist;

// K个一组翻转链表
public class Lc25 {
    public static void main(String[] args) {
        ListNode listNode = reverseKGroup(ListNode.construct(1, 2, 3, 4, 5), 2);
        System.out.println(ListNode.toString(listNode));
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int n = 0;
        while(cur!=null){
            n++;
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0,head);
        ListNode pre = null;
        ListNode p0 = dummy;
        ListNode next;
        while(n>=k){
            n-=k;
            cur = p0.next;
            for (int i = 0; i < k; i++) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            next = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            p0 = next;
        }
        return dummy.next;
    }
}
