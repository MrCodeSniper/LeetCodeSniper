package com.example.lib;

import com.example.lib.bean.TreeNode;

/**
 * 分治算法整理
 * @author ch
 */
public class DivideAndConquer {

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     *
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * <img width="300" height="300" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" alt="">

     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     *
     *
     * 3 | 9 | 20 15 7
     *
     * 3 | 9 | 20 | 15 | 7
     *
     *
     * Input: preorder = [-1], inorder = [-1]
     * Output: [-1]
     *
     * 先序遍历 根左右
     * 中序遍历 左根右
     * 这里根是节点 左右是子树
     *
     * 可以得到几个推论
     * 1。前序的第一个就是根节点
     * 2。从inorder找到根节点 那么根节点左边为左子树 右边为右子树 根据左右元素的数量
     * 可以从先序中找到从根开始 1个 为左 后面三个为右
     *
     * 这些推论用于根节点 如何让子树也能用 可以对左右子树进行递归
     *
     * 将问题化为子问题 每次对树的操作 我们可以分为根 左子树和右子树
     * 左子树内部确定顺序 需要左子树的先序和中序 右子树同理 而这些可以在根的先序和中序中获取
     *
     * 比如 3 | 9 | 20 15 7    9 ｜ 3 ｜ 15,20,7
     * 再进一步 左子树 9 | 9
     * 右子树 20 | 15 |7        15|20|7  这时候得到子树的根节点 与根节点建立关系即可
     *
     * 边界条件为 序列大小为1 直接返回该节点 为0 return null
     *
     * 这样每次拿到序列递归执行 在内部对序列再操作 即可
     * 递归执行 log2n 深度
     * 时间复杂度O(n*log2n)  空间复杂度O(n)
     *
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        return build(preorder,inorder);
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int rootIndex = -1;
        for(int i=0;i<inorder.length;i++){
            if(root.val == inorder[i]){
                rootIndex = i; //找到根节点在中序的下标
            }
        }
        //区分开左子树和右子树的的前序和中序列
        if(rootIndex!=-1){
            int leftNum = rootIndex;
            int rightNum = inorder.length-rootIndex-1;
            int[] leftInorder = new int[leftNum];
            for(int i=0;i<leftNum;i++){
                leftInorder[i] = inorder[i];
            }
            int[] rightInorder = new int[rightNum]; //right
            int index = 0;
            for(int i=inorder.length-rightNum;i<inorder.length;i++){
                rightInorder[index] = inorder[i];
                index++;
            }
            int [] leftPreorder = new int[leftNum];
            for(int i =1 ;i<preorder.length;i++){
                if(leftNum == 0){
                    break;
                }
                leftPreorder[i-1] = preorder[i];
                leftNum--;
            }
            int [] rightPreorder = new int[rightNum];
            int index2= 0;
            for(int i =preorder.length-rightNum ;i<preorder.length;i++){
                if(rightNum == 0){
                    break;
                }
                rightPreorder[index2] = preorder[i];
                index2++;
            }
            root.left = build(leftPreorder,leftInorder);
            root.right = build(rightPreorder,rightInorder);
        }
        return root;
    }

    //根节点的构建也可以合并到递归流程中
    public static TreeNode build(int[] preorder,int[] inorder) {
        if(preorder.length == 0){
            return null;
        }
        TreeNode node = new TreeNode(preorder[0]);
        if(preorder.length == 1 || inorder.length == 1){
            return node;
        }
        int rootIndex = -1;
        for(int i=0;i<inorder.length;i++){
            if(node.val == inorder[i]){
                rootIndex = i; //找到根节点在中序的下标
            }
        }
        if(rootIndex!=-1){
            int leftNum = rootIndex;
            int rightNum = inorder.length-rootIndex-1;
            int[] leftInorder = new int[leftNum];
            for(int i=0;i<leftNum;i++){
                leftInorder[i] = inorder[i];
            }
            int[] rightInorder = new int[rightNum];
            int index = 0;
            for(int i=inorder.length-rightNum;i<inorder.length;i++){
                rightInorder[index] = inorder[i];
                index++;
            }
            int [] leftPreorder = new int[leftNum];
            for(int i =1 ;i<preorder.length;i++){
                if(leftNum == 0){
                    break;
                }
                leftPreorder[i-1] = preorder[i];
                leftNum--;
            }
            int [] rightPreorder = new int[rightNum];
            int index2= 0;
            for(int i =preorder.length-rightNum ;i<preorder.length;i++){
                if(rightNum == 0){
                    break;
                }
                rightPreorder[index2] = preorder[i];
                index2++;
            }
            node.left = build(leftPreorder,leftInorder);
            node.right = build(rightPreorder,rightInorder);
        }
        return node;
    }

}
