package com.example.lib;

import com.example.lib.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先遍历和深度优先遍历算法题
 * @author ch
 */
public class BFS_DFS {


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
     * 有一幅以m x n的二维整数数组表示的图画image，其中image[i][j]表示该图画的像素值大小。
     * <p>
     * 你也被给予三个整数 sr , sc 和 newColor 。你应该从像素image[sr][sc]开始对图像进行 上色填充 。
     * <p>
     * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。
     * 将所有有记录的像素点的颜色值改为newColor。
     * <p>
     * 最后返回 经过上色渲染后的图像。
     * <p>
     * 广度优先遍历 头节点入队列 头节点出队 头节点子节点依次入队  遍历子节点出队 重复流程 直到所有节点出队
     * <p>
     * 输入: image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
     * 输出: [[2,2,2],[2,2,0],[2,0,1]]
     * <p>
     * 输入: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
     * 输出: [[2,2,2],[2,2,2]]
     * <p>
     * <p>
     * 解法1：广度优先遍历 利用队列 将子节点(上下左右)加入并入栈 循环出栈 遇到之前遍历的过滤 或不相同的则不入栈
     * 层次性的
     * <p>
     * 队列取 将所有子元素入队尾 再取   类似一层一层取
     * <p>
     * 时间复杂度O(n) 空间复杂度O(n)
     * <p>
     * 解法2：深度优先遍历
     * <p>
     * 利用栈的特性 栈中取 将所有子元素入栈 下次取就是取子元素 以叶子节点为结束
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int row = image.length; //3
        int column = image[0].length; //3

        //子元素 分别为上下左右
        Stack<Nodes> stack = new Stack<>();
        Nodes init = new Nodes(sr, sc, image);
        stack.push(init);
        int oldColor = image[sr][sc];
        System.out.println("oldColor:" + oldColor);
        image[sr][sc] = newColor;
        while (!stack.isEmpty()) {
            Nodes pop = stack.pop();
            System.out.println("遍历节点:" + pop.toString());
            if (pop.sr - 1 >= 0) { //顶部的坐标大于等于0 入栈
                Nodes newNode = pop.topNode(image);
                if (image[newNode.sr][newNode.sc] != newColor) {
                    if (newNode.value == oldColor) {
                        image[newNode.sr][newNode.sc] = newColor;
                        stack.push(newNode);
                    }
                }
            }
            if (pop.sr + 1 < row) { //底部的坐标小于行数 入栈
                Nodes newNode = pop.bottomNode(image);
                if (image[newNode.sr][newNode.sc] != newColor) {
                    if (newNode.value == oldColor) {
                        image[newNode.sr][newNode.sc] = newColor;
                        stack.push(newNode);
                    }
                }
            }
            if (pop.sc - 1 >= 0) { //
                Nodes newNode = pop.leftNode(image);
                if (image[newNode.sr][newNode.sc] != newColor) {
                    if (newNode.value == oldColor) {
                        image[newNode.sr][newNode.sc] = newColor;
                        stack.push(newNode);
                    }
                }
            }
            if (pop.sc + 1 < column) { //
                Nodes newNode = pop.rightNode(image);
                if (image[newNode.sr][newNode.sc] != newColor) {
                    if (newNode.value == oldColor) {
                        image[newNode.sr][newNode.sc] = newColor;
                        stack.push(newNode);
                    }
                }
            }
        }
        return image;
    }

    /**
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     * <p>
     * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
     * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
     * <p>
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * <p>
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0
     * <p>
     * 岛屿问题
     * <p>
     * 输入：grid =
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     * [0,0,0,0,0,0,0,1,1,1,0,0,0],
     * [0,1,1,0,1,0,0,0,0,0,0,0,0],
     * [0,1,0,0,1,1,0,0,1,0,1,0,0],
     * [0,1,0,0,1,1,0,0,1,1,1,0,0],
     * [0,0,0,0,0,0,0,0,0,0,1,0,0],
     * [0,0,0,0,0,0,0,1,1,1,0,0,0],
     * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 输出：6
     * <p>
     * 深度优先遍历 找到从1开始遍历到最多的情况
     *
     * @param grid
     * @return
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            //System.out.println(arr[i]);//arr中元素：2个数组的地址
            //遍历arr[0]，arr中元素第一个数组
            System.out.println("");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + ",");
                if (grid[i][j] == 1) {
                    int area = getArea(new BFS_DFS.Nodes(i, j, grid), grid);
                    if (maxArea <= area) {
                        maxArea = area;
                    }
                }
            }
        }
        System.out.println("岛屿最大面积:" + maxArea);
        return maxArea;
    }

    public static int getArea(BFS_DFS.Nodes node, int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        Stack<BFS_DFS.Nodes> stack = new Stack<>();
        stack.push(node);
        int area = 1;
        while (!stack.isEmpty()) {
            BFS_DFS.Nodes pop = stack.pop();
            System.out.println("出栈:" + pop.toString());
//            System.out.println("grid:"+grid[pop.sr][pop.sc]);
            if (pop.sr < 0 || pop.sr >= row || pop.sc < 0 || pop.sc >= column || grid[pop.sr][pop.sc] == -1) {

            } else {
                for (int i = 0; i < 4; i++) { //遍历四个方向
                    int x1 = pop.sr + dx[i], y1 = pop.sc + dy[i];
                    if (x1 >= 0 && y1 >= 0 && x1 < row && y1 < column) {
//                        System.out.println("x1:"+x1+",y1:"+y1+",grid value:"+grid[x1][y1]);
                        if (grid[x1][y1] == 1) { //四个方向存在为1的值 入栈
                            stack.push(new BFS_DFS.Nodes(x1, y1, grid));
                            grid[x1][y1] = -1;
                            //这里
                            area = area + 1;
                            System.out.println("当前面积为:" + area);
                        }
                    }
                }
                //遍历完节点的四个方向入栈后 要将此节点为-1 避免无限循环
                grid[pop.sr][pop.sc] = -1;
            }
        }
        return area;
    }

    /**
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     *
     * 给定二叉树: [3,9,20,null,null,15,7], 返回[3,9,20,15,7]
     *
     * 思路:
     * 1.层序遍历 通过对列实现 时间复杂度O(n) 空间复杂度O(n)
     * 2.
     *
     *
     *
     *
     * add 增加一个元索 如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     *
     * remove 移除并返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
     *
     * element 返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
     *
     * offer 添加一个元素并返回true 如果队列已满，则返回false
     *
     * poll 移除并返问队列头部的元素 如果队列为空，则返回null
     *
     * peek 返回队列头部的元素 如果队列为空，则返回null
     *
     * put 添加一个元素 如果队列满，则阻塞
     *
     * take 移除并返回队列头部的元素 如果队列为空，则阻塞
     *
     * @param root
     * @return
     */
    public static int[] levelOrder(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()){
            TreeNode node = nodes.poll();
            if(node!=null){
                results.add(node.val);
                if(node.left!=null){
                    nodes.add(node.left);
                }
                if(node.right!=null){
                    nodes.add(node.right);
                }
            }
        }
        int[] d = new int[results.size()];
        for(int i = 0;i<results.size();i++){
            d[i] = results.get(i);
        }
        return d;
    }

    public static class Nodes {
        public int sr;
        public int sc;
        public int[][] image;
        public int value;

        public Nodes(int sr, int sc, int[][] image) {
            this.sr = sr;
            this.sc = sc;
            this.value = image[sr][sc];
        }

        public Nodes rightNode(int[][] image) {
            return new Nodes(sr, sc + 1, image);
        }

        public Nodes leftNode(int[][] image) {
            return new Nodes(sr, sc - 1, image);
        }

        public Nodes topNode(int[][] image) {
            return new Nodes(sr - 1, sc, image);
        }

        public Nodes bottomNode(int[][] image) {
            return new Nodes(sr + 1, sc, image);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "sr=" + sr +
                    ", sc=" + sc +
                    ", value=" + value +
                    '}';
        }
    }
}
