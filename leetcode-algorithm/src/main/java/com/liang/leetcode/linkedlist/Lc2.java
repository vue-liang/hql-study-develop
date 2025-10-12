package com.liang.leetcode.linkedlist;
// 两数相加
public class Lc2 {
    public static void main(String[] args) {
        Lc2 lc2 = new Lc2();
        ListNode listNode = lc2.addTwoNumbers(ListNode.construct(2, 4, 3), ListNode.construct(5, 6, 4));
        ListNode listNode1 = lc2.addTwoNumbers(ListNode.construct(9,9,9,9,9,9,9), ListNode.construct(9,9,9,9));
        System.out.println(ListNode.toString(listNode));
        System.out.println(ListNode.toString(listNode1));
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null|| carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
        }
        return head.next;
    }
}
