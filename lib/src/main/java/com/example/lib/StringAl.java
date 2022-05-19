package com.example.lib;

import static com.example.lib.ArrayAl.reverseString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringAl {

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

}
