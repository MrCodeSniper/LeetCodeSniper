package com.example.lib.utils;

import com.example.lib.bean.Node;
import com.example.lib.bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    public static void printTree(TreeNode result) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(result);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                System.out.println("弹出为空");
            } else {
                System.out.println("弹出为" + poll.val);
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
        }
    }

    public static void printTree(Node result) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(result);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll == null) {

            } else {
                if (poll.next != null) {
                    System.out.println("弹出为" + poll.val + ",next为" + poll.next.val);
                } else {
                    System.out.println("弹出为" + poll.val + ",next为空");
                }
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
        }
    }
}
