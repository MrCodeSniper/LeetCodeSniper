package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyClass {

    public static void main(String[] args){
//        int target = 15;
//        int pos = search(nums,0,nums.length-1,target);
//        System.out.println("目标下标为:"+pos);

//        moveZeroes(nums3);
//        System.out.println(Arrays.toString(nums3));

        checkInclusionDemo();
    }

    public static void checkInclusionDemo(){
        boolean success = checkInclusion("ab","eidbaooo");
        boolean success2 = checkInclusion("ab","eidboaoo");
        boolean success3 = checkInclusion("a","eidboaoo");
        boolean success4 = checkInclusion("ab","ab");
        boolean success5 = checkInclusion("hello","ooolleoooleh");
        System.out.println("checkInclusion:"+success);
        System.out.println("checkInclusion2:"+success2);
        System.out.println("checkInclusion3:"+success3);
        System.out.println("checkInclusion4:"+success4);
        System.out.println("checkInclusion5:"+success5);
    }

    public static void middleNodeDemo(){
        ListNode first = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6,null))))));
        ListNode second = new ListNode(1);
        middleNode(second).printNode();
    }

    public static void removeNthFromEndDemo(){
        ListNode first = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null)))));
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(1,new ListNode(2,null));
        ListNode result = removeNthFromEnd(first,3);
        if(result == null){
            System.out.println("当前result为空!!");
        }else{
            result.printNode();
        }
    }

    /**
     * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
     *
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     *
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     *
     * 输入：s1= "ab" s2 = "eidboaoo"
     * 输出：false
     *
     * 思路 若s1长为n s2 双指针 左边为0 右边为n-1 每次循环 0～n-1的字符 如果都在s1里则retrun true
     * 如果不在则 0+1,n-1+1 继续走循环 如果右边到达s2的尾部即循环结束
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) { //"hello","ooolleoooleh"
        int start = 0;
        int end = s1.length()-1;
        while (end <= s2.length()-1){
            boolean isAllMatch = true;
            for(int i = start;i<=end;i++){ //不是包含就可以 而是需要保持顺序
                Character sub = s2.charAt(i);
                map.get(sub)
            }
            if(isAllMatch){
                return true;
            }
            start++;
            end++;
        }
        return false;
    }

    /**
     * 无重复子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * a
     *
     * b
     *
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     *
     * 思路 ?????? TODO 不是很清晰
     * 1.遍历字符串 固定一个值 遍历后续字符串 如果发现重复字符 则进入下一个循环 每次循环记录子串的长度 取大值
     * 时间复杂度O(n^2) 空间复杂度O(n)
     *
     * 2.滑动窗口 双指针的一种运用 通过寻找到数学规律应用
     *
     * 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，
     * 其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 rk
     *
     * 在每一步的操作中，我们会将左指针向右移动一格，
     * 表示 我们开始枚举下一个字符作为起始位置，
     * 然后我们可以不断地向右移动右指针，
     * 但需要保证这两个指针对应的子串中没有重复的字符。
     * 在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
     * 我们记录下这个子串的长度
     *
     * 在枚举结束后，我们找到的最长的子串的长度即为答案
     *
     * 时间复杂度O(n) 空间复杂度O(n)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        Set<Character> occ = new HashSet<Character>();
        int end = -1;//右边指针
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            //每次循环左指针往右挪一格 并从集合中删除该元素
            if(i>0){
                occ.remove(s.charAt(i-1));
            }
            while(end+1 <chars.length && !occ.contains(s.charAt(end+1))){ //右指针到达最后一个节点退出循环 如果后续的字符已存在集合内退出循环
                //每次循环 将不重复的子串元素加入集合
                occ.add(s.charAt(end+1));
                end++;
            }
            //每次循环计算maxLength
            if(maxLength< occ.size()){
                maxLength = occ.size();
            }
        }
        return maxLength;
    }


    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            List<Character> list = new ArrayList<>();
            list.add(chars[i]);
             for(int j=i+1;j<chars.length;j++){
                 char now = chars[j];
                 if(list.contains(now)){
                     break;
                 }else{
                     list.add(chars[j]);
                 }
             }
             if(maxLength< list.size()){
                 maxLength = list.size();
             }
        }
        return maxLength;
    }

    public static void lengthOfLongestSubstringDemo(){
        String s = "abcabcbb";
        String s1 = "bbbbb";
        String s2 = "pwwkew";
        int length = lengthOfLongestSubstring2(s);
        System.out.println("lengthOfLongestSubstringDemo1:"+length);
//        System.out.println("lengthOfLongestSubstringDemo2:"+lengthOfLongestSubstring(s1));
//        System.out.println("lengthOfLongestSubstringDemo3:"+lengthOfLongestSubstring(s2));
    }

    /**
     * 删除链表的倒数第 N 个结点
     *
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     *
     * 输入：head = [1], n = 1
     * 输出：[]
     *
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     *
     * 解法1。 准备两个指针 pre 指向被删除节点的前一个节点 可为null del 删除节点 默认为第一个  遍历列表
     * pre从-n开始出发 del从-n+1开始出发默认值为第一个 遍历一次进1 直到尾部 del的节点为要删除的节点
     * 直接pre.next = del.next 修改原链表即可
     * 边界问题 如果只有一个元素 返回空 如果pre为空 直接返回del.next
     *
     * 时间复杂度O(n)  空间复杂度O(1)
     *
     *
     * 解法2.多次遍历和容器存储法
     *
     * 一次遍历将节点的下标和值放入容器 遍历完毕后 得到倒数第N的下标和 之前的下标
     * 得到节点后 直接pre.next = del.next 修改原链表即可
     *
     * 时间复杂度O(n)  空间复杂度O(1)
     *
     *
     * 解法3.利用栈的特性 想到倒数和反转就要想起栈
     * 第一次遍历 入栈 倒数第n即弹栈第n次就是要删除的节点
     * n+1次.next = n.next 即可
     *
     * 时间复杂度O(n)  空间复杂度O(1)
     *
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
        int delIndex = -n+1;
        ListNode delNode = head;

        int preIndex = -n;
        ListNode preNode = null;
        while (now.next!=null){
            delIndex++;
            if(delIndex == 0){
                delNode = head;
            }else if(delIndex>=0){
                delNode = delNode.next;
            }
            preIndex++;
            if(preIndex == 0){
                preNode = head;
            }else if(preIndex>=0){
                preNode = preNode.next;
            }
            now = now.next;
        }
        //得到了 删除节点 和之前节点 和最后节点now
        //将之前节点指向删除节点的下一个节点

        if(preNode == null){
            return delNode.next;
        }else{
            preNode.next = delNode.next;
        }

        return head;
    }

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     *
     * 解法1.通过遍历获取到中间点 使用容器存储所有点位 通过中间下标获取中间节点
     * 时间复杂度O(N) 空间复杂度O(N)
     *
     * 解法2.优化上面的解法 每遍历一次计算中间节点暂存 空间复杂度为O(1)
     * 或者两次遍历 第一次确定长度和middle 第二次定位middle
     *
     * 解法3.双指针快慢指针 slow+1 fast+2 fast到底slow必定在中间
     * 时间复杂度O(N) 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        Map<Integer,ListNode> map  = new HashMap<>();

        ListNode now = head;
        int index = 0;
        map.put(index,now);
        while (now.next!=null){ //保证所有不为空的数据都加入容器
            index++;
            map.put(index,now.next);
            now = now.next;
        }
        int middle;
        if(index%2 == 0){
            //如果能够整除表示唯一确定中间点
            middle = index / 2;
        }else{
            //如果不能整除取中间+1
            middle = index / 2 + 1;
        }
        return map.get(middle);
    }

    /**
     *
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     *
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     *
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     *
     * 你所设计的解决方案必须只使用常量级的额外空间。
     *
     *
     *
     *
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     *
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
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            map.put(numbers[i],i);
        }
        for(int i=0;i<numbers.length;i++){
            int divide = target - numbers[i];
            if(map.get(divide)!=null){
                if(i<map.get(divide)){
                    indexs[0] = i+1;
                    indexs[1] = map.get(divide)+1;
                }else{
                    indexs[1] = i+1;
                    indexs[0] = map.get(divide)+1;
                }
                return indexs;
            }
        }
        return indexs;
    }

    public static void twoSumDemo(){
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int[] index = twoSum(nums,9);
        System.out.println(Arrays.toString(index));
    }


    public static void rotateDemo(){
        int[] nums = new int[]{1,2,3,4,5,6,7};
        rotate(nums,3);
        System.out.println(Arrays.toString(nums));
    }

    public static void reverseDemo(){
        char[] s = new char[]{'h','e','l','l','o'};
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
     *
     * 1.考察的题目类型 双指针
     *
     *  输入：s = ["h","e","l","l","o"]
     *  输出：    ["o","l","l","e","h"]
     *
     * @param s
     */
    public static void reverseString(char[] s) {
        int start = 0;
        int end  = s.length-1;
        while (start<=end){
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
     *
     * 1.考察的题目类型 双指针
     *  按照空格分割成多个数组进行反转
     *
     *  输入：s = "Let's take LeetCode contest"
     *  输出：    "s'teL ekat edoCteeL tsetnoc"
     * @param s
     */
    public static String reverseWords(String s) {
        List<Integer> ints = new ArrayList<>();
        ints.add(-1);
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length-1;i++){
            if(chars[i] == ' '){
                ints.add(i);
            }
        }
        ints.add(s.length());
        for(int i =0 ;i<ints.size()-1;i++){
            reverseString(chars,ints.get(i)+1,ints.get(i+1)-1);
        }
        return new String(chars);
    }


    public static void reverseString(char[] s,int start,int end) {
        while (start<=end){
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
     *
     * 左指针指向当前已经处理好的序列的尾部 即交换完的尾部
     * 右指针指向待处理序列的头部
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int i = 0 ,j = nums.length-1;
        while (i<=j){
            if(nums[i] == 0){
                for(int k = i;k<j;k++){
                    nums[k] = nums[k+1];
                }
                nums[j] = 0;
                j--;
            }else{
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
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
//        for (int i = 0;i<k;i++){
//            singleRotate(nums);
//        }
        if(nums.length <= 1){
            return;
        }
        int[] results = new int[nums.length];
//        k = nums.length-k; //右移3 等于左移4 即i+4位置的值 如果i+4>最大下标 取余
        for(int i = 0;i<nums.length;i++){
            if((i+k)<=nums.length-1){ //如果右移没有超出范围
                results[i+k] = nums[i];
            }else{ //如果右移超出范围
                int j = (i+k)%nums.length;
                results[j] = nums[i];
            }
        }
        for(int i = 0;i<nums.length;i++){
            nums[i]  =results[i];
        }
    }

    public static void singleRotate(int[] nums){
        int[] results = new int[nums.length];
        for(int i = 0;i<nums.length-1;i++){
            results[i+1] = nums[i];
        }
        results[0] = nums[nums.length-1];
        for(int i = 0;i<nums.length;i++){
            nums[i]  =results[i];
        }
    }

    /**
     * 有序数组的平方
     * 双指针 数组的前后各一个指针 将绝对值小的插入后 i++或j-- 直到i>j i=j为相遇 
     * @param nums 旧数组
     * @return 新数组
     */
    public static int[] sortedSquares(int[] nums) {
        int[] results = new int[nums.length];

        //从0和最大值出发 取大的数加入到数组最后一位 如果是正则-- 如果是负则++ 直到相遇
        //这样的好处是下标一定在范围内无需判断越界
        for(int i = 0,j = nums.length-1,index=nums.length-1;i<=j;){ //将i++ 将由函数体内执行
            int start  =nums[i];
            int end  =nums[j];
            if(Math.abs(start)>=Math.abs(end)){
                results[index--] = start*start;
                i++;
            }else {
                results[index--] = end*end;
                j--;
            }
        }
        System.out.println(Arrays.toString(results));
        return results;
    }

    /**
     * 获取正负数的列表
     * @param nums 数据源
     * @param  isPlus 是否为正
     * @return
     */
    public static List<Integer> getSubNumList(int[] nums,boolean isPlus){
        List<Integer> results = new ArrayList<>();
        if(isPlus){
            for(int i=0;i<nums.length;i++){
                if(nums[i]>0){
                    results.add(nums[i]);
                }
            }
        }else {
            for(int j=nums.length-1;j>=0;j--){
                if(nums[j]<0){
                    results.add(nums[j]);
                }
            }
        }
        return results;
    }

    /**
     * 找到0元素的下标
     * @param nums
     * @return
     */
    public static List<Integer> getZeroSubList(int[] nums){
        List<Integer> zeros = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 0){
                zeros.add(nums[i]);
            }
        }
        return zeros;
    }






    /**
     * 二分查找While循环
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        int middle = (end+start)/2;
        while(middle>=0 && start<=end){
            if(nums[middle] == target){
                return middle;
            }else if(nums[middle]>target){
                end = middle-1;
            }else if(nums[middle]<target){
                start = middle+1;
            }
            middle = (end+start)/2;
        }
        return -1;
    }

    /**
     * 二分查找递归方式
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int start,int end,int target){
        int middle = (start+end)/2;
        if(start>end){
            return -1;
        }
        if(nums[middle] == target){
            return middle;
        }else if(nums[middle]>target){
            return search(nums,start,middle-1,target);
        }else if(nums[middle]<target){
            return search(nums,middle+1,end,target);
        }
        return -1;
    }

}