package com.example.lib;

import com.example.lib.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先遍历和深度优先遍历算法题
 * @author ch
 */
public class BFS_DFS {

    /**
     * 辅助栈
     */
    public static Deque<Integer> path = new LinkedList<Integer>();

    public static void dfs(TreeNode root){
        if(root == null){
            return;
        }
        path.offerLast(root.val);
        System.out.println("val:"+ root.val);
        dfs(root.left);
        dfs(root.right);
        path.pollLast();
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


    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * 给定二叉树: [3,9,20,null,null,15,7],返回其层次遍历结果：
     *
     * 相同层级的放入List 所有层级放入根List
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 思路 根据返回的结果 需要我们明确的区分 哪些节点处于同一层级
     *
     * 准备一个列表 存入根节点 随即循环 新列表存入根节点的左右 再次循环 里面的节点将每个节点左右存放到新列表 以此类推
     *
     * 时间复杂度O(n) O(n)
     *
     *
     * 解答:
     * 特例处理： 当根节点为空，则返回空列表 [] ；
     * 初始化： 打印结果列表 res = [] ，包含根节点的队列 queue = [root] ；
     * BFS 循环： 当队列 queue 为空时跳出；
     * 新建一个临时列表 tmp ，用于存储当前层打印结果；
     * 当前层打印循环： 循环次数为当前层节点数（即队列 queue 长度）；
     * 出队： 队首元素出队，记为 node；
     * 打印： 将 node.val 添加至 tmp 尾部；
     * 添加子节点： 若 node 的左（右）子节点不为空，则将左（右）子节点加入队列 queue ；
     * 将当前层结果 tmp 添加入 res 。
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> rootResult = new ArrayList<>();
        rootResult.add(root.val);
        results.add(rootResult);
        List<TreeNode> nowCell = new ArrayList<>();
        nowCell.add(root);

        while (!nowCell.isEmpty()){
            List<TreeNode> newCell = new ArrayList<>();
            List<Integer> group  = new ArrayList<>();
            for(int i =0;i<nowCell.size();i++){
                TreeNode node = nowCell.get(i);
                if(node!=null){
                    if(node.left!=null){
                        newCell.add(node.left);
                        group.add(node.left.val);
                    }
                    if(node.right!=null){
                        newCell.add(node.right);
                        group.add(node.right.val);
                    }
                }
            }
            if(!group.isEmpty()){
                results.add(group);
            }
            nowCell = newCell;
        }
        return results;
    }

    /***
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * 思路
     * 1.深度优先遍历DFS
     * 2.层级遍历 将每层的节点存到数组 最终层级就是这个数组的长度
     * 3.递归计算 递归计算左右子树的深度
     * 4.广度优先遍历 队列里放入 每一次全部出队 层级+1 直到没有元素即可
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;
        //递归计算左右子树的深度
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth,rightMaxDepth)+1; //如果左右子树都为空 那么层级为他自身1
    }

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     * 迪杰斯特拉算法 计算最短路径
     *
     * 思路 1.导出所有 跟节点到叶子节点的组合 取出和target相同的
     * 2.递归思想 左右子树寻找target-root的点 当target-root =0  表示刚好找到节点
     * 3.DFS 每次深度遍历可以生成路径 需要注意把不符合条件的poll了 符合条件的路径保存  递归回硕思想
     *
     *
     *
     *<img width="640" height="320" src="https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg" alt="">
     *
     * targetSum = 22
     *
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root,target);
        List<List<Integer>> newlist = new ArrayList<>();
        newlist.addAll(ret);
        ret.clear();
        return newlist;
    }

    /**
     * 临时变量存储匹配的路径列表
     */
    public static List<List<Integer>> ret = new LinkedList<>();

    public static void dfs(TreeNode root,int target){
        if(root == null){
            return;
        }
        path.offerLast(root.val); //用额外的队列来存储遍历路径
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) { //如果左右子树为空 target又为0则就是这个节点
            ret.add(new LinkedList<>(path)); //确定最佳路径 新的集合后续path的poll不会影响
        }
        dfs(root.left,target);
        dfs(root.right,target);
        path.pollLast(); //利用递归特性 路径排除最后一个不符合条件的
    }



    /**
     * 1. node 3  find 27
     * 2. node 9  18
     * 3. return null
     * 4.node  20  find 7
     * 5.node 15 find 2
     * 6.null 2
     * 7.node 7  0
     * @param root
     * @param target
     * @return
     */
    public static TreeNode findLastNode(TreeNode root,int target){
        if(root == null){
            System.out.println("递归到空节点,target:"+target);
            return null;
        }
        if(target == 0){
            System.out.println("递归到正确叶子节点,node:"+root.val+",target:"+target);
            return root;
        }
        if(root.left!=null){
            TreeNode left = findLastNode(root.left,target-root.left.val);
            if(left!=null){
                return left;
            }
        }
        if(root.right!=null){
            TreeNode right = findLastNode(root.right,target-root.right.val);
            if(right!=null){
                return right;
            }
        }
        return null;
    }

    /**
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * 核心为反转 反转想到什么 栈
     *
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 结果为
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * 思路1. 先中后三序遍历实现
     * 思路2. 层序遍历 对数组进行reverse
     * 思路3. 递归思想
     * 思路4。核心为反转 反转想到什么 栈 反之使用队列
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        reverseLevelOrder(root);
        List<List<Integer>> results = new ArrayList<>(rets);
        rets.clear();
        return results;
    }

    /**
     *
     * @param root 当前递归节点 递归是深度操作
     * @param isReverse 下一层是否反转遍历
     */
    public static void reverseLevelOrder2(TreeNode root,Boolean isReverse){
        if(root == null) return;
        System.out.println("node:"+root.val);
        if(isReverse){
            reverseLevelOrder2(root.right,false);
            reverseLevelOrder2(root.left,false);
        }else {
            reverseLevelOrder2(root.left,true);
            reverseLevelOrder2(root.right,true);
        }
    }

    public static List<List<Integer>> rets = new ArrayList<>();

    /**
     *            3
     *      *    / \
     *      *   9  20
     *      *  /\   / \
     *      * 1  2  15 7
     *
     *      第一次 3
     *      2.   20 9
     *      3.   1 2  15 7
     *         输出
     *     0    3          3
     *     1    20 9      9 20
     *     2    1 2 15 7
     *
     * @param root
     */
    public static void reverseLevelOrder(TreeNode root){
        if(root == null) return;
        System.out.println("node:"+root.val);
        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> nodes = new ArrayList<>();
        stack.push(root);
        int index = 0;
        //如果奇数层判断栈是否为空 不为空进行栈操作  如果偶数层判断队列是否为空 不为空进行队列操作
        while (!stack.isEmpty()){
            List<Integer> stackValue = new ArrayList<>();
            while (!stack.isEmpty()){ //把栈内的所有元素按照规则导出成列表
                TreeNode node = stack.pop();
                if(node!=null){
//                    System.out.println("出栈:"+node.val);
                    stackValue.add(node.val);
                    if(index%2==0){ //用一个栈存储 调整入栈顺序
                        nodes.add(node.left);
                        nodes.add(node.right);
                    }else {
                        nodes.add(node.right);
                        nodes.add(node.left);
                    }
                }
            }
            if(!stackValue.isEmpty()){
                rets.add(stackValue);
            }
            for(int i=0;i<nodes.size();i++){
                stack.push(nodes.get(i));
            }
            nodes.clear();
            index++;
        }
    }

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树: root =[6,2,8,0,4,7,9,null,null,3,5]
     *
     * <img width="320" height="320" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png" alt="">
     *
     * 示例 1:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * 示例 2:
     *
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     *
     * 二叉排序树的特性能否利用
     * 怎么找到父节点
     * 思路 1.遍历一遍二叉树 利用唯一的特性 存放到Map中 key为节点Val value为key的父节点
     * 先1边不变 比如q不动 找到p的父节点  如果父节点为q 那么return q 如果不是q 那么继续找父节点 直到根节点结束
     * 如果一遍后都找不到 那么找到q的父节点 p继续上述流程直到 q到根节点
     *
     * 时间复杂度O(log2n*log2n) 空间复杂度O(n)
     *
     * 思路 2 递归
     *
     *
     * 思路 3 回朔
     *
     *
     * 在寻找节点的过程中，我们可以顺便记录经过的节点，这样就得到了从根节点到被寻找节点的路径 路径是唯一的
     * 那么问题就简化为 找到两个路径的唯一重复点 两个链表 双遍历即可
     * 
     * 解答
     * 通过两次遍历得到两个路径 然后再两个路径循环找到结果 时间复杂度O(n) 空间复杂度O(n)
     * 
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //1.遍历二叉树存储每个节点的父节点信息
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode out = stack.pop();
            if(out.left!=null){
                parentTreeNodeMap.put(out.left.val,out);
                stack.push(out.left);
            }
            if(out.right!=null){
                parentTreeNodeMap.put(out.right.val,out);
                stack.push(out.right);
            }
        }
        //利用二叉排序树的特性 如果p.val < q.val 那么q一定在p的根节点或者根节点的右边或者在其右子树
        //1.小的不动 大的找父节点 找相同 如果到根节点还不相同 小的找父节点 大的重复 直到为根
        if(p.val<q.val){
            TreeNode now = p;
            while (now!=null){
                TreeNode bigger = q;
                while (bigger!=null){
                    if(bigger == now){
                        parentTreeNodeMap.clear();
                        return now;
                    }
                    bigger = parentTreeNodeMap.get(bigger.val);
                }
                //P的父节点往上
                now = parentTreeNodeMap.get(now.val);
            }
        }else {
            TreeNode now = q;
            while (now!=null){
                TreeNode bigger = p;
                while (bigger!=null){
                    if(bigger == now) {
                        parentTreeNodeMap.clear();
                        return now;
                    }
                    bigger = parentTreeNodeMap.get(bigger.val);
                }
                //P的父节点往上
                now = parentTreeNodeMap.get(now.val);
            }
        }
        parentTreeNodeMap.clear();
        return null;
    }

    public static Map<Integer,TreeNode> parentTreeNodeMap = new HashMap<>();


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
