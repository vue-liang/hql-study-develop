package com.liang.leetcode.binarytree;

import java.util.*;
public class TreeBuilder {
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int idx = 1;
        while (!queue.isEmpty() && idx < arr.length) {
            TreeNode node = queue.poll();

            // 构造左子节点
            if (arr[idx] != null) {
                node.left = new TreeNode(arr[idx]);
                queue.offer(node.left);
            }
            idx++;

            // 构造右子节点
            if (idx < arr.length && arr[idx] != null) {
                node.right = new TreeNode(arr[idx]);
                queue.offer(node.right);
            }
            idx++;
        }
        return root;
    }
//    List<Integer> res = new ArrayList<>();
//    public List<Integer> inorderTraversal(TreeNode root) {
//        if(root==null) return res;
//        inorderTraversal(root.left);
//        res.add(root.val);
//        inorderTraversal(root.right);
//        return res;
//    }
}
