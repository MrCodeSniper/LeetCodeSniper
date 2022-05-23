package com.example.lib;

import static com.example.lib.BFS_DFS.floodFill;
import static com.example.lib.BFS_DFS.maxAreaOfIsland;
import static com.example.lib.BFS_DFS.mergeTrees;
import static com.example.lib.LinkAl.mergeTwoLists2;
import static com.example.lib.LinkAl.middleNode;
import static com.example.lib.LinkAl.removeNthFromEnd;
import static com.example.lib.LinkAl.reverse;
import static com.example.lib.StringAl.checkInclusion;
import static com.example.lib.StringAl.lengthOfLongestSubstring2;
import static com.example.lib.TreeAl.connect;
import static com.example.lib.utils.TreeUtils.printTree;

import com.example.lib.bean.ListNode;
import com.example.lib.bean.Node;
import com.example.lib.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _Demo {

    public static void main(String[] args) {
        stackQueueDemo();
    }

    public static void demo(){
        ListNode first = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))));
        ListNode second = new ListNode(1);

        ListNode a = new ListNode(2,new ListNode(4,new ListNode(3)));
        ListNode b = new ListNode(5,new ListNode(6,new ListNode(4)));

        ListNode c = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode d = new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9))));

        ListNode e = new ListNode(0);
        ListNode f = new ListNode(0);

        LinkAl.addTwoNumbers(e,f).printNode();
//        LinkAl.addTwoNumbers(first,second).printNode();
//        LinkAl.addTwoNumbers(c,d).printNode();

//        LinkAl.addTwoNumbers(new ListNode(0),new ListNode(0)).printNode();
    }

    public static void stackQueueDemo(){
        StackAl.CQueue obj = new StackAl.CQueue();
        obj.appendTail(1);
        obj.appendTail(2);
        obj.appendTail(3);
        int param_2 = obj.deleteHead();
        int param_3 = obj.deleteHead();
        System.out.println("删除节点为:"+param_2+","+param_3);
    }

    public static void dynamicProgramDemo() {
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
//        System.out.println("结果为:"+dynamicProgram.minimumTotal(group));

        System.out.println("结果为:" + dynamicProgram.rob(new int[]{6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3}));
        System.out.println("结果为:" + dynamicProgram.rob(new int[]{1, 2, 3, 1}));
        System.out.println("结果为:" + dynamicProgram.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println("结果为:" + dynamicProgram.rob(new int[]{1, 1, 1, 1}));
    }

    public static void bitOpDemo() {
        int nums[] = {2, 2, 1};
        int nums2[] = {4, 1, 2, 1, 2};
        BitOp bitOp = new BitOp();
        int value = bitOp.singleNumber(nums2);
        System.out.println("value:" + value);

        bitOp.reverseBits(43261596);
    }

    public static void reverseListDemo() {
        ListNode ln1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
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

    public static void lengthOfLongestSubstringDemo() {
        String s = "abcabcbb";
        String s1 = "bbbbb";
        String s2 = "pwwkew";
        int length = lengthOfLongestSubstring2(s);
        System.out.println("lengthOfLongestSubstringDemo1:" + length);
//        System.out.println("lengthOfLongestSubstringDemo2:"+lengthOfLongestSubstring(s1));
//        System.out.println("lengthOfLongestSubstringDemo3:"+lengthOfLongestSubstring(s2));
    }


    public static void twoSumDemo() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] index = SearchAl.twoSum(nums, 9);
        System.out.println(Arrays.toString(index));
    }


    public static void rotateDemo() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayAl.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    public static void reverseDemo() {
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        ArrayAl.reverseString(s);
        System.out.println(Arrays.toString(s));
        String s1 = "Let's take LeetCode contest";
        String reverse = StringAl.reverseWords(s1);
        System.out.println(reverse);
    }
}