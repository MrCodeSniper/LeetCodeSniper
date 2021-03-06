package com.example.lib;

import static com.example.lib.ArrayAl.reverseString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.TextUI;

public class StringAl {

    /**
     * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
     *
     *
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     *
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     *
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     *
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     *
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * 说明：
     *
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−2^31, 2^31− 1]。如果数值超过这个范围，请返回 INT_MAX (231− 1) 或INT_MIN (−231) 。
     *
     * 问题: 如何转化为整数 进行运算
     *
     * 思路1 遍历字符 按照题目的条件 进行判断
     *
     * 边界判断 字符过界 0000x的情况 条件处理
     *
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     *
     * @param str
     * @return
     */
    public static int strToInt(String str) {
        str  = str.trim();
        if(str.isEmpty()) return 0;
        char first = str.charAt(0);
        //只允许首位字符为+ - 或者0~9 根据ASCII码判断字符范围
        if(first == '+' || first == '-' || first>=48 && first<= 57){
            if(first == '+'){
                str = str.replace('+',' ').trim();
            }
            if(first == '-'){
                str = str.replace('-',' ').trim();
            }
            int flag = 0;
            if(first == '-'){
                flag = -1;
            }
            long result = 0;
            //先正序遍历一遍遍历到数字为止进行截取
            int startIndex = 0;
            int endIndex = str.length();
            for(int i=0;i<str.length()-1;i++){
                char now = str.charAt(i);
                if(now>=48 && now<= 57){
                    startIndex = i;
                    break;
                }
            }
            for(int i=startIndex;i<str.length()-1;i++){
                char now = str.charAt(i);
                if(now<48 || now>57){
                    endIndex = i;
                    break;
                }
            }
            str = str.substring(startIndex,endIndex);
            int zeroEndIndex = 0;
            if(str.startsWith("0")){
                for(int i=0;i<str.length();i++){
                    char now = str.charAt(i);
                    if(now!=48){
                        zeroEndIndex = i;
                        break;
                    }
                }
            }
            str = str.substring(zeroEndIndex);
            long multiply = (long) Math.pow(10,str.length()-1);
            for(int i=startIndex;i<str.length();i++){
                char now = str.charAt(i);
                if(now>=48 && now<= 57){
                    int nowInt = getIntByAscii(now);
                    result+=multiply*nowInt;
                    multiply/=10;
                    char lastChar = str.charAt(str.length()-1);
                    if(verification((int) (result/10),getIntByAscii(lastChar),flag) == 1){
                        if(flag == -1){
                            return Integer.MIN_VALUE;
                        }else {
                            return Integer.MAX_VALUE;
                        }
                    }
                }
            }

            if(flag == -1){
                result = result*-1;
            }
            return (int)result;
        }
        return 0;
    }

    /**
     * -91283472332
     * int 是否越界检验算法
     * 通过循环将数字x的每一位拆开，在计算新值时每一步都判断是否溢出
     * 溢出条件有两个
     * 一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE
     * 设当前计算结果为ans，下一位为pop
     * 从ans * 10 + pop > MAX_VALUE这个溢出条件来看
     * 当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
     * 当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
     *
     * 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
     * 当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
     * 当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
     *
     *
     * @param number        需要校验的数字
     * @param valueOfCharAt 需要校验的最低位
     * @param flag          正负标志
     * @return
     */

    public static int verification(int number, int valueOfCharAt, int flag) {
        long shangjie = (long) Math.pow(2, 31) - 1;     // 上限
        long xiajie = -(long) Math.pow(2, 31);          // 下限
        int result = 0;
        if (flag == -1) {                               // 负数校验
            if (((-number) < xiajie / 10) || (-number == (xiajie / 10) && valueOfCharAt > 8)) {
                System.out.println("负整数越界");
                result = 1;
            }
        } else {                                        // 正数校验
            if ((number > shangjie / 10) || ((number == shangjie / 10) && valueOfCharAt > 7)) {
                System.out.println("正整数越界");
                result = 1;
            }
        }
        return result;
    }

    public static int getIntByAscii(char character){
        switch (character){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
        }
        return -1;
    }

    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素
     *
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     *
     * 提示 题目类型为搜索和回溯
     *
     * abcdefg
     *
     * 从简到繁
     *
     * a
     *
     * ab
     *
     * abc
     *
     * aabb
     *
     * 可能的组合个数为Cn n-1  =  C3 2 = 3*2*1 = 6
     *
     * 每一次确定前面1个树  a ab  ac   如果剩下的数为1那么结束
     *
     * 思路
     * 1.对字符进行循环 每次确定一个字符 跟子串 递归确定子串的组合 再合并组合生成列表
     * 递归退出的条件为 字符输入为1 那么数组就为1 如果为2 则有两种可能
     * 使用map缓存递归的结果 提高时间效率
     *
     * 边界处理 重复的情况  使用HashSet对结果集进行去重
     *
     * 效率分析 时间复杂度 O(N^2)其中N为字符串的长度  空间复杂度为O(N)
     *
     * @param s
     * @return
     */
    public static String[] permutation(String s) {
        map.clear();
        map.put("",new String[]{});
        return permutation2(s);
    }

    //用缓存记录 中间计算结果
    public static HashMap<String,String[]> map =new HashMap<>();

    public static String[] permutation2(String s) {
        if(map.get(s)!=null){
            return map.get(s);
        }
        if(s.length() == 1){
            String[] value = new String[]{s};
            map.put(s,value);
            return value;
        }
        if(s.length() == 2){
            char a = s.charAt(0);
            char b = s.charAt(1);
            String[] value;
            if(a == b){
                value = new String[]{a+""+b};
            }else {
                value = new String[]{a+""+b,b+""+a};
            }
            map.put(s,value);
            return value;
        }
        char[] chars = s.toCharArray();
        HashSet<String> results = new HashSet<>();
        for(int i = 0;i<chars.length;i++){
            char selectedChar = chars[i]; //b
            System.out.println("selectedChar为:"+selectedChar);
            //选定一个字符串之后 得到剩余子串的组合
            String input = s.substring(0,i)+s.substring(i+1);
            System.out.println("input为:"+input);
            String[] combine = permutation2(input); //bc,cb
            map.put(input,combine);
            StringBuilder builder;
            for(int j=0;j<combine.length;j++){
                builder = new StringBuilder();
                builder.append(selectedChar);
                builder.append(combine[j]);
                results.add(builder.toString());
            }
            System.out.println("results为:"+results);
        }
        String[] combine = new String[results.size()];
        Iterator<String> iterator = results.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            combine[index] = iterator.next();
            index++;
        }
        //得到最新数组
        return combine;
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
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 思路: 字符串转为字符数组 因为一个字符改为三个字符 把数组扩容到3倍 保证不会越界
     * 遍历字符数组 如果不是空格 加到新数组 如果是空格加 %20 三个字符到新数组
     *
     *
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        char[] results = new char[chars.length*3];
        int index = 0; //用来取旧值
        int resultIndex = 0; //用来赋新值
        while (index<chars.length){
            char text = chars[index];
            if(text == ' '){
                results[resultIndex] = '%';
                results[resultIndex+1] = '2';
                results[resultIndex+2] = '0';
                resultIndex = resultIndex+3;
                index++;
            }else {
                results[resultIndex] = text;
                index++;
                resultIndex++;
            }
        }
        return new String(results).trim(); //去除空格
//        return s.replace(" ","%20");
    }

    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     *
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     *
     * 思路
     * 1.循环字符数组 把前k个取出为新数组 再把后续的数 放入结果数组中 再加上新数组 时间复杂度和空间复杂度都是O(N)
     * 2.subString 拼接...
     * 3.多次翻转  abcdefg
     * reverse(s,0,num);    gfedcba  基于中间翻转 前k个数到了最后
     * reverse(s,0,num-n);  cdefgba  把后面翻转到前部分的顺序恢复
     * reverse(s,num-n+1,num); cdefgab 再把后续的顺序恢复
     *
     *
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        int length = s.length();
        char[] result = new char[length];
        char[] chars = s.toCharArray();
        char[] newArray = new char[n];
        for(int i=0;i<chars.length;i++){
            if(i<n){  //把前k个取出为新数组
                newArray[i] = chars[i];
            }else{ //再把后续的数 放入结果数组中
                result[i-n] = chars[i];
            }
        }
        for(int i=0;i<newArray.length;i++){
            result[length-n+i] = newArray[i];
        }
        return new String(result).trim();
//        return s.substring(n,length)+s.substring(0,n);
    }
}
