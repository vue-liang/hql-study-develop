package com.liang.leetcode.linkedlist;

import java.util.Stack;

/**
 * TODO 好好学学递归
 * 1. 找终止条件：本题终止条件很明显，当递归到链表为空或者链表只剩一个元素的时候，没得交换了，自然就终止了。
 * 2. 找返回值：返回给上一层递归的值应该是已经交换完成后的子链表。
 * 3. 单次的过程：因为递归是重复做一样的事情，所以从宏观上考虑，只用考虑某一步是怎么完成的。
 */
public class Lc24 {
    public static void main(String[] args) {
        ListNode head = ListNode.construct(1, 2, 3, 4, 5);
        Lc24 lc24 = new Lc24();
        System.out.println(ListNode.toString(lc24.swapPairs(head)));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
