package com.example.lib;

import com.example.lib.bean.Node;
import com.example.lib.bean.TreeNode;


import java.util.LinkedList;

public class TreeAl {

    /**
     * 请实现两个函数，分别用来序列化和反序列化二叉树。
     *
     * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。
     * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     *
     * 可以使用#作为数组元素的连接
     *
     * 通过层序遍历进行序列化转化为字符串 为了完整的表现树的结构 需要将null也拼接进序列化结果
     * 时间复杂度O(n)空间复杂度O(n)
     *
     * 完美二叉树 存在内存限制 构造数组会超出
     *
     * 反序列化 在于区分# 转化为字符串数组
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) { //[1,2,3,null,null,4,5]
        StringBuilder builder = new StringBuilder();
        //通过层序遍历遍历树得到序列化结果
        LinkedList<TreeNode> queue = new LinkedList<>(); //辅助队列
        LinkedList<Integer> indexQueue = new LinkedList<>(); //辅助队列
        queue.add(root);
        indexQueue.add(0);
        int lastIndex = 0;
        while (!queue.isEmpty() && !indexQueue.isEmpty()){ //为了完整的表现树的结构 需要将null也拼接进序列化结果
            TreeNode node = queue.poll();
            int nowIndex = indexQueue.poll();
            if(node != null){ //为了完整的表现树的结构 需要完美的二叉树 null
                //在添加进去之前要判断该节点的下标 前面需要补几个null
                if(nowIndex-lastIndex>1){
                    for(int i=0;i<nowIndex-lastIndex-1;i++){
                        builder.append("null");
                        builder.append("#");
                    }
                }
                builder.append(node.val);
                builder.append("#");
                int leftIndex = nowIndex*2+1;
                if(node.left!=null){
                    queue.add(node.left);
                    indexQueue.add(leftIndex);
                }
                if(node.right!=null){
                    queue.add(node.right);
                    int rightIndex =  leftIndex+1;
                    indexQueue.add(rightIndex);
                }
                lastIndex = nowIndex;
            }
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) { //1#2#3#null#null#4#5#null#null#null#null#
        String[] decodeStrings = data.split("#");
        TreeNode root = null;
        //规律 下标为i的根节点 其左右节点为 其 2*i+1    2*i+2
        //比如根节点 1 下标为0  左子数下标为1
        //比如节点2  下标为1 左子数下标为3
        //比如节点3  下标为2 左子数下标为5
        //下标为5  左节点下标为 11
        //如果遇到null 则break
        int size = decodeStrings.length;
        if(size>0){
            if(decodeStrings[0].equals("null")) return null;
            root = new TreeNode(Integer.parseInt(decodeStrings[0]));
        }
        //进行遍历赋值
        LinkedList<TreeNode> queue = new LinkedList<>(); //辅助队列
        LinkedList<Integer> indexQueue = new LinkedList<>(); //辅助队列
        queue.add(root);
        indexQueue.add(0);
        while (!queue.isEmpty()&&!indexQueue.isEmpty()){
            TreeNode node = queue.poll();
            int nowIndex = indexQueue.poll();
            if(node!=null){
                int leftIndex = nowIndex*2+1;
                int rightIndex =  leftIndex+1;
                if(leftIndex<size && !decodeStrings[leftIndex].equals("null")){
                    TreeNode leftNode =  new TreeNode(Integer.parseInt(decodeStrings[leftIndex]));
                    node.left = leftNode;
                    queue.add(node.left);
                    indexQueue.add(leftIndex);
                }
                if(rightIndex<size && !decodeStrings[rightIndex].equals("null")){
                    TreeNode rightNode =  new TreeNode(Integer.parseInt(decodeStrings[rightIndex]));
                    node.right = rightNode;
                    queue.add(node.right);
                    indexQueue.add(rightIndex);
                }
            }
        }
        return root;
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * <img width="640" height="320" src="https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png" alt="">
     *
     * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     *
     * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
     *
     * <img width="640" height="320" src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png" alt="">
     *
     * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
     *
     * 思路1 利用二叉搜索树的特性 中序遍历 得到 1->2->3->4->5  后序遍历得到 5->4->3->2->1 将两次遍历得到的最后节点 建立双向关联得到结果链表
     * 得到左子树的最大节点和根节点建立联系 内部中序遍历左右子树 建立双向联系 头部和尾部通过临时变量存储 遍历完后建立头尾联系
     * 时间复杂度O(n) 空间复杂度O(n)
     *
     * 思路2 每次递归记录当前节点和之前的节点 cur 和 pre 建立联系 先从左叶子开始 再到右叶子节点 用双指针确定位置关系
     * 递归执行 最后对头尾进行联系
     * 时间复杂度O(n) 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public static Node treeToDoublyList(Node root) {
        if(root == null) return null;
        if(root.left==null && root.right==null){
           root.left = root;
           root.right = root;
           return root;
        }
        head = null;
        tail = null;
        leftMax = null;
        preNode(root);
        afterNode(root);
        if(root.left!=null){
            isLeft = true;
            Node left = middleOrder(root.left);
            if(leftMax == null){
                leftMax = left;
            }
            if(leftMax!=null){
                leftMax.right = root;
            }
            root.left = leftMax;
        }
        if(root.right!=null){
            isLeft = false;
            Node rightMin = middleOrder(root.right);
            root.right = rightMin;
            rightMin.left = root;
        }
        if(tail!=null){
            tail.right = head;
            if(head!=null){
                head.left = tail;
            }
        }
        return head;
    }


    Node head1, pre;

    /**
     * 标准答案
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        if(root==null) return null;
        dfs(root);

        pre.right = head1;
        head1.left =pre;//进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的

        return head1;

    }

    public void dfs(Node cur){
        if(cur==null) return;
        dfs(cur.left);

        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if(pre==null) head1 = cur;
            //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
        else pre.right = cur;

        cur.left = pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。

        pre = cur;//pre指向当前的cur
        dfs(cur.right);//全部迭代完成后，pre指向双向链表中的尾节点
    }

    /**
     * 中序遍历
     * 将整个树看成 根节点 左子右子 根节点需要根左子树的最右叶子节点连接 即可
     *
     * @param root
     */
    public static Node middleOrder(Node root){
        if(root == null)  return null;
        Node left = middleOrder(root.left);
        if(left!=null){
            left.right = root;
            root.left = left;
        }
        Node right = middleOrder(root.right);
        if(right!=null){
            root.right = right;
            right.left = root;
            if(isLeft){
                if(leftMax == null){
                    leftMax = right;
                }
            }
        }
        return root;
    }

    public static Node preNode(Node root){
        if(root == null)  return null;
        Node left = preNode(root.left);
        if(left!=null)  if(head == null) head = left;
        if(head == null) head = root;
        Node right = preNode(root.right);
        if(right!=null)  if(head == null) head = right;
        return root;
    }

    public static Node afterNode(Node root){
        if(root == null)  return null;
        Node right = afterNode(root.right);
        if(right!=null)  if(tail == null) tail = right;
        if(tail == null) tail = root;
        Node left = afterNode(root.left);
        if(left!=null)  if(tail == null) tail = left;
        return root;
    }

    //头节点指针
    public static Node head = null;

    //是否为左子树
    public static boolean isLeft = false;

    public static Node leftMax = null;

    //尾节点指针
    public static Node tail = null;


    /**
     * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     *
     * 左<根<右
     *
     * 输入: root = [3,1,4,null,2], k = 1
     * 输出: 4
     *
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 输出: 4
     *
     * 最右边的叶子节点肯定最大
     *
     * 边界条件 可以有重复元素
     *
     * 思路1. 递归 每次把最大的取出 k-1直到K=0 时间复杂度O(n) 空间复杂度O(1)
     *
     * 思路2. 二叉搜索树进行遍历得到有序数组 即可得到第K个最大值 左中右 先序遍历 时间复杂度O(n) 空间复杂度O(n)
     *
     * @param root
     * @param k
     * @return
     */
    public static int kthLargest(TreeNode root, int k) {
        while (k>0){
            TreeNode max = findMax(root);
//            System.out.println("打印:"+max.val);
            if(k==1){
                return max.val;
            }
            max.val = -1;
            k--;
        }
        return -1;
    }

    /**
     * 每次递归过程
     * @param root
     * @return
     */
    public static TreeNode findMax(TreeNode root){
        if(root == null) return null;
        if(root.val == -1){
            //表示已经遍历了
            if(root.left == null){
                return null;
            }else{
                return findMax(root.left);
            }
        }
        if(root.right == null){
            return root;
        }else {
            TreeNode node = findMax(root.right);
            if(node == null){
                return root;
            }else {
                return node;
            }
        }
    }

    /**
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     * 思路一 得到镜像树 遍历路径相同
     *
     * 解答
     * 1.用一个队列 循环 根据左右加入树 再根据右左 pop的时候pop两次 如果对称值对不上 或者存在空return false 否则return true
     * 2.递归解法 递归检查
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
          return check(root,root);
    }

    public static boolean check(TreeNode p,TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p == null || q ==null) {
            return false;
        }
        if(q.val !=p.val){
            return  false;
        }
        return check(p.left,q.right) && check(p.right,q.left);
    }

    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        return reverse(root);
    }

    /**
     * 官方解法
     * 先转换 后赋值 从底到上
     * @param root
     * @return
     */
    public static TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree2(root.left);  //得到新的左子树
        TreeNode right = mirrorTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 递归对左右子树进行交换
     * @param treeNode
     * @return
     */
    public static TreeNode reverse(TreeNode treeNode){
        if(treeNode == null) return null;
        TreeNode temp =  treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
        reverse(treeNode.left);
        reverse(treeNode.right);
        return treeNode;
    }

    /**
     * 合并二叉树
     * 给你两棵二叉树： root1 和 root2 。
     * <p>
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
     * 你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     * <p>
     * 返回合并后的二叉树。
     * <p>
     * 注意: 合并过程必须从两个树的根节点开始。
     * <p>
     * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * [1,3,2,5,null,null,null] [2,1,3,null,4,null,7,null,null,null,null]
     * 输出：[3,4,5,5,4,null,7]
     * 示例 2：
     * <p>
     * 输入：root1 = [1], root2 = [1,2]
     * 输出：[2,2]
     * <p>
     * 思路 层次遍历 广度优先 相加
     * <p>
     * 思路 深度优先遍历 递归方式 将重复的操作抽出
     * <p>
     * 每次操作为合并的流程
     * 1。如果1树节点为空 取2树为新节点
     * 2。如果2树节点为空 取1树为新节点
     * 3。如果1，2都存在则加起来 取1为节点
     * <p>
     * 重复节点的左和右子树进行合并并返回
     *
     * @param root1
     * @param root2
     * @return
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }


    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点
     * <p>
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有next 指针都被设置为 NULL。
     * <p>
     * 输入：root = [1,2,3,4,5,6,7]
     * 输出：[1,#,2,3,#,4,5,6,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
     * <p>
     * <p>
     * 左子树的右节点 next 右子树的左节点
     * <p>
     * 递归方式
     * <p>
     * 每次递归 做一次左节点next 右节点 但这不能让左子树 和右子树 next连接
     * 这里用到了父节点的next 让其可以在递归中根据父的next 设置 root.right.next = root.next.left; 处理好右节点
     * 接下里就树递归左右 和非空判断即可
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        if (root != null) {
            if (root.left != null) {
                root.left.next = root.right;
                if (root.next != null) { //如果父节点有next则 子右next为 next的左
                    root.right.next = root.next.left;
                } else {
                    root.right.next = null;
                }
                connect(root.left);
                if (root.right != null) {
                    connect(root.right);
                }
            }
        }
        return root;
    }
}
