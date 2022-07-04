package com.example.lib;

import com.example.lib.bean.TreeNode;

import java.util.HashMap;

/**
 * 分治算法整理
 * @author ch
 */
public class DivideAndConquer {


    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题
     *
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     *
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     *
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     *
     * 思路 递归计算每一次执行n-1 如果n>0 自身*自身 如果n<0 1/自身*1/自身  直到N=0为止
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * 递归如果n过大存在栈溢出的风险 需要辅助空间存储每次计算结果
     *
     * 优化 层数减少 可以使用 计算的结果 * 计算的结果  2^4 =  2^2 * 2^2 只需要一次计算
     * 层数可以减少为O(log2n) 空间复杂度O(1)
     *
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        if (n == 1) return x;
        double half = myPow(x, n / 2);
        //如果整除 平方处理 如果非整除 表示计算到了最后一个 直接乘x 无需乘X^2
        return n % 2 == 0 ? half * half : half * half * (n > 0 ? x : 1 / x);
    }

    public static double myPow3(double x, int n) {
        map.clear();
        if(n == 0) return 1;
        map.put(0,x);
       return myPow2(x,n-1);
    }

    public static HashMap<Integer,Double> map = new HashMap<>();

    public static double myPow2(double x, int n) {
        if(map.get(n)!=null){
            return map.get(n);
        }
        if(n<0){
            n++;
            double num = myPow2(x,n);
            map.put(n,num);
            return 1/x*num;
        }else if(n>0){
            n--;
            double num = myPow2(x,n);
            map.put(n,num);
            return x*num;
        }
        return x;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     *
     * 输入: [1,6,3,2,5]
     * 输出: false
     *
     * 输入: [1,3,2,6,5]
     * 输出: true
     *
     *      5
     *     / \
     *    2   6
     *   / \
     *  1   3
     *
     * 二叉搜索树具备的特性 左子树小于根节点 右子树大于根节点
     *
     * 后序遍历的特性 左右根
     *
     * 拆分子问题 合并
     *
     * 数组的第一个数必须是最小  且为叶子节点 最后一个为根节点
     *
     * 设遍历后序 [i,j]区间
     * 设找到根节点索引为m
     * 那么左子树[i,m-1] < postorder[j]
     * 那么右子树[m,j-1] > postorder[j]
     *
     * 时间复杂度 O(N^2) 空间复杂度 O(N)
     *
     * 进行双循环判断
     *
     * 如果i>=j 说明数组只有一个元素返回true
     * recur(i,m−1) ： 判断 此树的左子树 是否正确。
     * recur(m, j - 1)recur(m,j−1) ： 判断 此树的右子树 是否正确。
     *
     * 利用这些特性整理出规律 利用辅助单调栈能 缩减时间复杂度
     *
     * 时间复杂度 O(N) 空间复杂度 O(N)
     *
     *
     * @param postorder 后序遍历结果
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder,0,postorder.length-1);
    }

    public boolean verifyPostorder(int[] postorder,int start,int end) {
        if(start>=end) return true;
        int p = start;
        while (postorder[p]<postorder[end]){ //从序列找到小于根节点的元素下标 的最大值为新子树的根节点
            p++;
        }
        int m = p; //记录为根节点
        while(postorder[p] > postorder[end]){ //从根节点开始 是否找到了结尾都是大于根节点的 如果全都大则刚好到末尾
            p++;
        }
        return p == end && verifyPostorder(postorder,start,m-1) && verifyPostorder(postorder,m,end-1);

    }

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
