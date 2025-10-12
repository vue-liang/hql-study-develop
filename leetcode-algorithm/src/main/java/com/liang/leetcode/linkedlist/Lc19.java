package com.liang.leetcode.linkedlist;

// 删除链表中倒数第N个节点
// 1 2 3 n 5 6
public class Lc19 {
    public static void main(String[] args) {
        Lc19 lc19 = new Lc19();
        ListNode head = ListNode.construct(1, 2, 3, 4, 5);
        System.out.println(ListNode.toString(head));
        ListNode listNode = lc19.removeNthFromEnd(head, 5);
        System.out.println(ListNode.toString(listNode));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        ListNode q = dummy;
        while (p.next != null) {
            q = q.next;
            p = p.next;
        }
        q.next = q.next.next;
        return dummy.next;
    }
}
