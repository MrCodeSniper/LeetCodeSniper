package com.example.lib;

import com.example.lib.bean.Node;
import com.example.lib.bean.TreeNode;

public class TreeAl {

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
