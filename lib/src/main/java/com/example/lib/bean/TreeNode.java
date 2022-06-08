package com.example.lib.bean;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    public int val;
    public TreeNode left;
    public  TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void printTree(){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()){
            TreeNode pop =  queue.poll();
            System.out.print(pop.val+",");
            if(pop.val!=-1){
                if(pop.left == null && pop.right == null){
                    continue;
                }
                if(pop.left!=null){
                    queue.add(pop.left);
                }else {
                    queue.add(new TreeNode(-1));
                }
                if(pop.right!=null){
                    queue.add(pop.right);
                }else {
                    queue.add(new TreeNode(-1));
                }
            }
        }
        System.out.println(" ");
    }

    public String printTreeStr(){
        StringBuffer stringBuffer = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()){
            TreeNode pop =  queue.poll();
            stringBuffer.append(pop.val+",");
            if(pop.val!=-1){
                if(pop.left == null && pop.right == null){
                    continue;
                }
                if(pop.left!=null){
                    queue.add(pop.left);
                }else {
                    queue.add(new TreeNode(-1));
                }
                if(pop.right!=null){
                    queue.add(pop.right);
                }else {
                    queue.add(new TreeNode(-1));
                }
            }
        }
        return stringBuffer.toString();
    }
}
