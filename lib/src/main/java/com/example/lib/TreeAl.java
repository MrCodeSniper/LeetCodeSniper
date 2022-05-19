package com.example.lib;

import com.example.lib.bean.Node;

public class TreeAl {

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
