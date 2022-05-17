package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MyClass {

    public static void main(String[] args) {
//        int target = 15;
//        int pos = search(nums,0,nums.length-1,target);
//        System.out.println("目标下标为:"+pos);

//        moveZeroes(nums3);
//        System.out.println(Arrays.toString(nums3));

        dynamicProgramDemo();
    }

    public static void dynamicProgramDemo(){
        DynamicProgram dynamicProgram = new DynamicProgram();
//        System.out.println("结果为:"+dynamicProgram.climbStairs(45));
        List<List<Integer>> group = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        one.add(2);
        group.add(one);
        List<Integer> two = new ArrayList<>();
        two.add(3);
        two.add(4);
        group.add(two);

        List<Integer> three = new ArrayList<>();
        three.add(10);
        three.add(11);
        three.add(1);
        group.add(three);
        System.out.println("结果为:"+dynamicProgram.minimumTotal(group));
    }

    public static void bitOpDemo(){
        int nums[] = {2,2,1};
        int nums2[] = {4,1,2,1,2};
        BitOp bitOp = new BitOp();
        int value = bitOp.singleNumber(nums2);
        System.out.println("value:"+value);

        bitOp.reverseBits(43261596);
    }

    public static void reverseListDemo(){
        ListNode ln1 = new ListNode(1, new ListNode(2, new ListNode(3,new ListNode(4,new ListNode(5)))));
        reverse(ln1).printNode();
    }

    public static void mergeTwoListsDemo() {
        ListNode ln0 = new ListNode(5);
        ListNode ln1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode ln2 = new ListNode(1, new ListNode(3, new ListNode(4)));
//        mergeTwoLists(ln1, ln0).printNode();

        mergeTwoLists2(ln1, ln0).printNode();
    }

    public static void connectDemo() {
        Node root = new Node(1, new Node(2, new Node(4), new Node(5)), new Node(3, new Node(6), new Node(7)));
        connect(root);
        printTree(root);
    }

    public static void mergeTreesDemo() {
        TreeNode root1 = new TreeNode(1, new TreeNode(3, new TreeNode(5), null), new TreeNode(2));
        TreeNode root2 = new TreeNode(2, new TreeNode(1, null, new TreeNode(4)), new TreeNode(3, null, new TreeNode(7)));
        TreeNode result = mergeTrees(root1, root2);
        printTree(result);
    }

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

    public static void floodFillDemo() {
        int a[][] = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        int b[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int c[][] = floodFill(a, 1, 1, 2);
        System.out.println("|||||" + Arrays.deepToString(c));
    }

    public static void checkInclusionDemo() {
        boolean success = checkInclusion("ab", "eidbaooo");
        boolean success2 = checkInclusion("ab", "eidboaoo");
        boolean success3 = checkInclusion("a", "eidboaoo");
        boolean success4 = checkInclusion("ab", "ab");
        boolean success5 = checkInclusion("hello", "ooolleoooleh");
        System.out.println("checkInclusion:" + success);
        System.out.println("checkInclusion2:" + success2);
        System.out.println("checkInclusion3:" + success3);
        System.out.println("checkInclusion4:" + success4);
        System.out.println("checkInclusion5:" + success5);
    }

    public static void middleNodeDemo() {
        ListNode first = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))));
        ListNode second = new ListNode(1);
        middleNode(second).printNode();
    }

    public static void removeNthFromEndDemo() {
        ListNode first = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(1, new ListNode(2, null));
        ListNode result = removeNthFromEnd(first, 3);
        if (result == null) {
            System.out.println("当前result为空!!");
        } else {
            result.printNode();
        }
    }

    public static void maxAreaOfIslandDemo() {
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        int[][] grid2 = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
        };
        maxAreaOfIsland(grid);
    }

    public static ListNode reverse(ListNode head) {
        //将head看成1之后的节点 将指针指向上一个节点
        if(head != null && head.next == null){
            return head;
        }else if(head == null){
            return null;
        }
        ListNode last = reverse(head.next);
        //反转拿到最后的值开始设置 这里执行是4,5
        head.next.next = head;
        head.next = null;
        return last; //
    }

    /**
     * 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 1 2 3 4
     *
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     *
     * 递归将选取的过程思路统一
     *
     * 如果选了第n个数 其 其余的k-1个数从1～n-1范围内选取
     * 如果没选第n个数  其余的k个数从1~n-1范围内选取
     *
     * C(n,k) = C(n-1,k)+C(n-1,k-1)
     *
     * List =  1,2,3,4
     *
     * subString
     *
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        return null;
    }

    public void combine(List<Integer> nums,int n){
        List<Integer> result = new ArrayList<>();
        for (int i = 1;i<=n;i++){
            result.add(i);
        }
    }

    /**
     * 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 1 2 3 4 5    递归到尾部 5,null  返回5
     * 上一次递归 4,5     5->4  , 4->null return 5
     * 上一次递归 3,4  递归返回的为5  4->3 3->null
     * 上一次递归 2,3  递归返回的为5  3->2 2->null
     * 上一次递归 1,2  递归返回的为5  2->1 1->null
     * 返回的是根节点 每次递归修改指针
     * 完毕
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     *
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     *
     * 输入：head = [1,2]
     * 输出：[2,1]
     *
     * 看到反转 想到栈的特性 利用栈实现 时间复杂度O(n) 空间复杂度O(n)
     *
     * 递归方式 将操作看成可重复的独立操作
     *
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode now = head;
        while (now!=null){
            stack.push(new ListNode(now.val));
            now = now.next;
        }

        ListNode result = null;
        ListNode next = null;
        while (!stack.isEmpty()){
            ListNode item = stack.pop();
            if(result == null){
                result = item;
                next = result;
            }else {
                next.next = new ListNode(item.val);
                next = next.next;
            }
        }
        return result;
    }


    /**
     * 递归方式 将长步骤拆解  每一次调用mergeTwoLists2 都是2个节点 两两比较的过程 每次返回的结果是小的部分节点
     * 赋值给上个节点 也就是上个节点的next是小的节点
     * @param list1  1 2 4
     * @param list2  3
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if(list1.val<list2.val){  //每一次取小的 加到小的链表中
            list1.next  =  mergeTwoLists(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 输入：
     * l1 = [1,2,4],
     * l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode node1 = list1;
        ListNode node2 = list2;

        ListNode newNode; //先取出第一个节点 后续从下个节点开始
        if (node1.val < node2.val) {
            newNode = new ListNode(node1.val);
            node1 = node1.next;
        } else {
            newNode = new ListNode(node2.val);
            node2 = node2.next;
        }

        ListNode lastNode = newNode;

        while (node1 != null || node2 != null) {
            //遍历完长链表 每遍历一个节点 比较将小的节点纳入新链表
            //同时小的节点往后进一 如果遇到null则纳入另外一个节点 直到遍历完
            if (node1 == null) {
                lastNode.next = new ListNode(node2.val);
                node2 = node2.next;
            } else if (node2 == null) {
                lastNode.next = new ListNode(node1.val);
                node1 = node1.next;
            } else {
                //每次循环小的链表进一
                if (node1.val < node2.val) {
                    lastNode.next = new ListNode(node1.val);
                    node1 = node1.next;
                } else {
                    lastNode.next = new ListNode(node2.val);
                    node2 = node2.next;
                }
            }
            lastNode = lastNode.next;
        }
        return newNode;
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

    public static void connected(Node node) {
        if (node != null) {
            Node left = singleConnect(node.left); //内部排序
            Node right = singleConnect(node.right); //内部排序
            if (left != null && left.right != null) {
                left.right.next = right.left;
            }
            connected(node.left);
            connected(node.right);
        }
    }


    public static Node singleConnect(Node node) {
        if (node != null) {
            if (node.left != null) {
                node.left.next = node.right;
            }
            if (node.right != null) {
                node.right.next = null;
            }
        }
        return node;
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
                    int area = getArea(new Nodes(i, j, grid), grid);
                    if (maxArea <= area) {
                        maxArea = area;
                    }
                }
            }
        }
        System.out.println("岛屿最大面积:" + maxArea);
        return maxArea;
    }

    public static int getArea(Nodes node, int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        Stack<Nodes> stack = new Stack<>();
        stack.push(node);
        int area = 1;
        while (!stack.isEmpty()) {
            Nodes pop = stack.pop();
            System.out.println("出栈:" + pop.toString());
//            System.out.println("grid:"+grid[pop.sr][pop.sc]);
            if (pop.sr < 0 || pop.sr >= row || pop.sc < 0 || pop.sc >= column || grid[pop.sr][pop.sc] == -1) {

            } else {
                for (int i = 0; i < 4; i++) { //遍历四个方向
                    int x1 = pop.sr + dx[i], y1 = pop.sc + dy[i];
                    if (x1 >= 0 && y1 >= 0 && x1 < row && y1 < column) {
//                        System.out.println("x1:"+x1+",y1:"+y1+",grid value:"+grid[x1][y1]);
                        if (grid[x1][y1] == 1) { //四个方向存在为1的值 入栈
                            stack.push(new Nodes(x1, y1, grid));
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

    static class Nodes {
        int sr;
        int sc;
        int[][] image;
        int value;

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

    /**
     * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * <p>
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     * <p>
     * 输入：s1= "ab" s2 = "eidboaoo"
     * 输出：false
     * <p>
     * 思路 若s1长为n s2 双指针 左边为0 右边为n-1 每次循环 0～n-1的字符 如果数组中的元素个数相同即可
     * 如果不在则 0+1,n-1+1 继续走循环 如果右边到达s2的尾部即循环结束
     * <p>
     * 假设s1的长度为m s2的长度为n
     * 时间复杂度 O(n*m) 空间复杂度为O(26)
     * <p>
     * 解法1.滑动窗口方案相同 但是逻辑不同将n*m 平铺为O(n+m)
     * 每次循环 数组进位+1 丢弃的位置-1
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) { //"hello","ooolleoooleh"
        int start = 0;
        int end = s1.length() - 1;

        int[] cnt = new int[26];
        for (int i = 0; i < s1.length(); ++i) { //利用字母字符的特性 ascii码从a开始进位  通过数组的值表示字母的个数 个数相同表示 字符匹配且每个字母个数相同
            --cnt[s1.charAt(i) - 'a'];
        }
        //"ab","eidbaooo"
        while (end <= s2.length() - 1) {
            int[] cnt2 = new int[26];
            for (int i = start; i <= end; i++) { //不是包含就可以 每个字符的个数相等
                --cnt2[s2.charAt(i) - 'a'];
            }
            if (Arrays.equals(cnt, cnt2)) {
                return true;
            }
            start++;
            end++;
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) { //abcdefg
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) { //循环开始第一次将字符加入  ab 计数加1
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) { //判断一次
            return true;
        }
        for (int i = n; i < m; ++i) { //每移动一次 判断一次 把新的位置+1 把最前面的位置-1
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 无重复子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * a
     * <p>
     * b
     * <p>
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * <p>
     * 思路 ?????? TODO 不是很清晰
     * 1.遍历字符串 固定一个值 遍历后续字符串 如果发现重复字符 则进入下一个循环 每次循环记录子串的长度 取大值
     * 时间复杂度O(n^2) 空间复杂度O(n)
     * <p>
     * 2.滑动窗口 双指针的一种运用 通过寻找到数学规律应用
     * <p>
     * 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，
     * 其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 rk
     * <p>
     * 在每一步的操作中，我们会将左指针向右移动一格，
     * 表示 我们开始枚举下一个字符作为起始位置，
     * 然后我们可以不断地向右移动右指针，
     * 但需要保证这两个指针对应的子串中没有重复的字符。
     * 在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
     * 我们记录下这个子串的长度
     * <p>
     * 在枚举结束后，我们找到的最长的子串的长度即为答案
     * <p>
     * 时间复杂度O(n) 空间复杂度O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        Set<Character> occ = new HashSet<Character>();
        int end = -1;//右边指针
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //每次循环左指针往右挪一格 并从集合中删除该元素
            if (i > 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (end + 1 < chars.length && !occ.contains(s.charAt(end + 1))) { //右指针到达最后一个节点退出循环 如果后续的字符已存在集合内退出循环
                //每次循环 将不重复的子串元素加入集合
                occ.add(s.charAt(end + 1));
                end++;
            }
            //每次循环计算maxLength
            if (maxLength < occ.size()) {
                maxLength = occ.size();
            }
        }
        return maxLength;
    }


    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            List<Character> list = new ArrayList<>();
            list.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                char now = chars[j];
                if (list.contains(now)) {
                    break;
                } else {
                    list.add(chars[j]);
                }
            }
            if (maxLength < list.size()) {
                maxLength = list.size();
            }
        }
        return maxLength;
    }

    public static void lengthOfLongestSubstringDemo() {
        String s = "abcabcbb";
        String s1 = "bbbbb";
        String s2 = "pwwkew";
        int length = lengthOfLongestSubstring2(s);
        System.out.println("lengthOfLongestSubstringDemo1:" + length);
//        System.out.println("lengthOfLongestSubstringDemo2:"+lengthOfLongestSubstring(s1));
//        System.out.println("lengthOfLongestSubstringDemo3:"+lengthOfLongestSubstring(s2));
    }

    /**
     * 删除链表的倒数第 N 个结点
     * <p>
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * <p>
     * 输入：head = [1], n = 1
     * 输出：[]
     * <p>
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * <p>
     * 解法1。 准备两个指针 pre 指向被删除节点的前一个节点 可为null del 删除节点 默认为第一个  遍历列表
     * pre从-n开始出发 del从-n+1开始出发默认值为第一个 遍历一次进1 直到尾部 del的节点为要删除的节点
     * 直接pre.next = del.next 修改原链表即可
     * 边界问题 如果只有一个元素 返回空 如果pre为空 直接返回del.next
     * <p>
     * 时间复杂度O(n)  空间复杂度O(1)
     * <p>
     * <p>
     * 解法2.多次遍历和容器存储法
     * <p>
     * 一次遍历将节点的下标和值放入容器 遍历完毕后 得到倒数第N的下标和 之前的下标
     * 得到节点后 直接pre.next = del.next 修改原链表即可
     * <p>
     * 时间复杂度O(n)  空间复杂度O(1)
     * <p>
     * <p>
     * 解法3.利用栈的特性 想到倒数和反转就要想起栈
     * 第一次遍历 入栈 倒数第n即弹栈第n次就是要删除的节点
     * n+1次.next = n.next 即可
     * <p>
     * 时间复杂度O(n)  空间复杂度O(1)
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //head = [1,2], n = 2
        //head = [1], n = 1
        //head = [1,2,3,4,5], n = 2
        ListNode now = head;
        int delIndex = -n + 1;
        ListNode delNode = head;

        int preIndex = -n;
        ListNode preNode = null;
        while (now.next != null) {
            delIndex++;
            if (delIndex == 0) {
                delNode = head;
            } else if (delIndex >= 0) {
                delNode = delNode.next;
            }
            preIndex++;
            if (preIndex == 0) {
                preNode = head;
            } else if (preIndex >= 0) {
                preNode = preNode.next;
            }
            now = now.next;
        }
        //得到了 删除节点 和之前节点 和最后节点now
        //将之前节点指向删除节点的下一个节点

        if (preNode == null) {
            return delNode.next;
        } else {
            preNode.next = delNode.next;
        }

        return head;
    }

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     * <p>
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * <p>
     * 解法1.通过遍历获取到中间点 使用容器存储所有点位 通过中间下标获取中间节点
     * 时间复杂度O(N) 空间复杂度O(N)
     * <p>
     * 解法2.优化上面的解法 每遍历一次计算中间节点暂存 空间复杂度为O(1)
     * 或者两次遍历 第一次确定长度和middle 第二次定位middle
     * <p>
     * 解法3.双指针快慢指针 slow+1 fast+2 fast到底slow必定在中间
     * 时间复杂度O(N) 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();

        ListNode now = head;
        int index = 0;
        map.put(index, now);
        while (now.next != null) { //保证所有不为空的数据都加入容器
            index++;
            map.put(index, now.next);
            now = now.next;
        }
        int middle;
        if (index % 2 == 0) {
            //如果能够整除表示唯一确定中间点
            middle = index / 2;
        } else {
            //如果不能整除取中间+1
            middle = index / 2 + 1;
        }
        return map.get(middle);
    }

    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * <p>
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * <p>
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * <p>
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * <p>
     * <p>
     * <p>
     * <p>
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * <p>
     * 解法1：利用KV容器的特性 对应值和下标 时间空间都是O(n)
     * 解法2: 固定一个数 在剩余数组中利用升序的特性采用二分查找找到另外一个数  时间 O(nlogn) 空间O(1)
     * 解法3: 双指针固定首尾 如果差值在比尾部大 首部进一 否则尾部退一 若找不到 首部进一 尾部还原 时间O(n) 空间O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] indexs = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int divide = target - numbers[i];
            if (map.get(divide) != null) {
                if (i < map.get(divide)) {
                    indexs[0] = i + 1;
                    indexs[1] = map.get(divide) + 1;
                } else {
                    indexs[1] = i + 1;
                    indexs[0] = map.get(divide) + 1;
                }
                return indexs;
            }
        }
        return indexs;
    }

    public static void twoSumDemo() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] index = twoSum(nums, 9);
        System.out.println(Arrays.toString(index));
    }


    public static void rotateDemo() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    public static void reverseDemo() {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(Arrays.toString(s));
        String s1 = "Let's take LeetCode contest";
        String reverse = reverseWords(s1);
        System.out.println(reverse);
    }

    /**
     * 翻转数组
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 1.考察的题目类型 双指针
     * <p>
     * 输入：s = ["h","e","l","l","o"]
     * 输出：    ["o","l","l","e","h"]
     *
     * @param s
     */
    public static void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start <= end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 反转字符串中的单词
     * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * <p>
     * 1.考察的题目类型 双指针
     * 按照空格分割成多个数组进行反转
     * <p>
     * 输入：s = "Let's take LeetCode contest"
     * 输出：    "s'teL ekat edoCteeL tsetnoc"
     *
     * @param s
     */
    public static String reverseWords(String s) {
        List<Integer> ints = new ArrayList<>();
        ints.add(-1);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == ' ') {
                ints.add(i);
            }
        }
        ints.add(s.length());
        for (int i = 0; i < ints.size() - 1; i++) {
            reverseString(chars, ints.get(i) + 1, ints.get(i + 1) - 1);
        }
        return new String(chars);
    }


    public static void reverseString(char[] s, int start, int end) {
        while (start <= end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * i++ j--
     * 双指针 头尾如果头遇到了0 那么下标后面的元素都要左移一位 最后一个为0 用j指向0之前的下标 直到遍历完成
     * 直到 下标相遇
     * <p>
     * 左指针指向当前已经处理好的序列的尾部 即交换完的尾部
     * 右指针指向待处理序列的头部
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == 0) {
                for (int k = i; k < j; k++) {
                    nums[k] = nums[k + 1];
                }
                nums[j] = 0;
                j--;
            } else {
                i++;
            }
        }
    }

    /**
     * 轮转数组
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 解法1：遍历数组 将i+k处于数组范围内的赋值新数组   i+k超出的取余 (i+k)%nums.length;赋值 时空都是O(n)
     * 优化将数组改为常量存储 空间可以降到O(1)
     * 解法2：将每一次移位看成独立的函数 循环移动
     * 解法3：将K区间的数组看成整体区分两个进行排序
     * 解法4：数组翻转 将整体翻转倒序 然后翻转区间 实现区域内正序 和移动
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
//        for (int i = 0;i<k;i++){
//            singleRotate(nums);
//        }
        if (nums.length <= 1) {
            return;
        }
        int[] results = new int[nums.length];
//        k = nums.length-k; //右移3 等于左移4 即i+4位置的值 如果i+4>最大下标 取余
        for (int i = 0; i < nums.length; i++) {
            if ((i + k) <= nums.length - 1) { //如果右移没有超出范围
                results[i + k] = nums[i];
            } else { //如果右移超出范围
                int j = (i + k) % nums.length;
                results[j] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = results[i];
        }
    }

    public static void singleRotate(int[] nums) {
        int[] results = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            results[i + 1] = nums[i];
        }
        results[0] = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = results[i];
        }
    }

    /**
     * 有序数组的平方
     * 双指针 数组的前后各一个指针 将绝对值小的插入后 i++或j-- 直到i>j i=j为相遇
     *
     * @param nums 旧数组
     * @return 新数组
     */
    public static int[] sortedSquares(int[] nums) {
        int[] results = new int[nums.length];

        //从0和最大值出发 取大的数加入到数组最后一位 如果是正则-- 如果是负则++ 直到相遇
        //这样的好处是下标一定在范围内无需判断越界
        for (int i = 0, j = nums.length - 1, index = nums.length - 1; i <= j; ) { //将i++ 将由函数体内执行
            int start = nums[i];
            int end = nums[j];
            if (Math.abs(start) >= Math.abs(end)) {
                results[index--] = start * start;
                i++;
            } else {
                results[index--] = end * end;
                j--;
            }
        }
        System.out.println(Arrays.toString(results));
        return results;
    }

    /**
     * 获取正负数的列表
     *
     * @param nums   数据源
     * @param isPlus 是否为正
     * @return
     */
    public static List<Integer> getSubNumList(int[] nums, boolean isPlus) {
        List<Integer> results = new ArrayList<>();
        if (isPlus) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    results.add(nums[i]);
                }
            }
        } else {
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[j] < 0) {
                    results.add(nums[j]);
                }
            }
        }
        return results;
    }

    /**
     * 找到0元素的下标
     *
     * @param nums
     * @return
     */
    public static List<Integer> getZeroSubList(int[] nums) {
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros.add(nums[i]);
            }
        }
        return zeros;
    }


    /**
     * 二分查找While循环
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int middle = (end + start) / 2;
        while (middle >= 0 && start <= end) {
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else if (nums[middle] < target) {
                start = middle + 1;
            }
            middle = (end + start) / 2;
        }
        return -1;
    }

    /**
     * 二分查找递归方式
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int start, int end, int target) {
        int middle = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (nums[middle] == target) {
            return middle;
        } else if (nums[middle] > target) {
            return search(nums, start, middle - 1, target);
        } else if (nums[middle] < target) {
            return search(nums, middle + 1, end, target);
        }
        return -1;
    }

}