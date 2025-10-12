package com.liang.leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lc102 {
    public static void main(String[] args) {
        TreeNode root = TreeBuilder.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        Lc102 lc102 = new Lc102();
        System.out.println(lc102.levelOrder(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cap = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < cap; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(list);
        }
        return res;
    }
}
