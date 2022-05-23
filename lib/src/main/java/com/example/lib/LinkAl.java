package com.example.lib;

import com.example.lib.bean.ListNode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LinkAl {

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
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     *
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *
     * 如何处理进位
     *
     * 如果不考虑进位 循环链表
     *
     *
     * 利用栈的特性
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) { //1,2,3,4,5    3,4,5
        return changeNode(getLinkNum(l1).add(getLinkNum(l2)).toString());
    }

    public static ListNode changeNode(String sum){ //807
        char[] chars = sum.toCharArray();
        ListNode result = null;
        ListNode start = null;
        for(int i = chars.length-1;i>=0;i--){
            int num = Integer.parseInt(String.valueOf(chars[i]));
            if(result == null){
                result = new ListNode(num);
                start = result;
            }else{
                result.next = new ListNode(num);
                result = result.next;
            }
        }
        return start;
    }

    public static BigDecimal getLinkNum(ListNode l1){
        BigDecimal num1 = new BigDecimal(0);
        int multifyFactory = 1;
        ListNode node1 = l1;
        while (node1!=null){
            num1 = num1.add(new BigDecimal(node1.val).multiply(new BigDecimal(multifyFactory)));
            node1 = node1.next;
            multifyFactory*=10;
        }
        return num1;
    }

    public static Stack<ListNode> getStack(ListNode head){
        Stack<ListNode> stack = new Stack<>();
        ListNode now = head;
        while (now!=null){
            stack.push(new ListNode(now.val));
            now = now.next;
        }
        return stack;
    }

    public static int getListNodeSize(ListNode node){
        int i = 0;
        while (node!=null){
            i++;
            node = node.next;
        }
        return i;
    }


    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * 示例 1：
     *
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *
     * 思路: 1.利用栈的反转特性 时间复杂度O(n) 空间复杂度O(n)
     * 2.反转链表再打印 修改指针 递归改变每个节点
     * 3.技巧 遍历一个 放到数组末尾 循环
     *
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode now = head;
        while (now!=null){
            stack.push(new ListNode(now.val));
            now = now.next;
        }
        int[] result = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()){
            result[index] = stack.pop().val;
            index++;
        }
        return result;
    }
}
