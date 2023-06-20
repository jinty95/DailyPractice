package test.cn.jinty.util.string;

import cn.jinty.util.string.LCSUtil;
import org.junit.Test;

import java.util.Scanner;

/**
 * LCS - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class LCSUtilTest {

    @Test
    public void testLongestCommonSubstring() {
        String s1 = "Hello";
        String s2 = "He is very well";
        System.out.println(LCSUtil.longestCommonSubstringLength(s1, s2));
        System.out.println(LCSUtil.longestCommonSubstring(s1, s2));
        s1 = "I am very well now";
        s2 = "He is very well too";
        System.out.println(LCSUtil.longestCommonSubstringLength(s1, s2));
        System.out.println(LCSUtil.longestCommonSubstring(s1, s2));
    }

    @Test
    public void testlongestCommonSubsequence() {
        String s1 = "选择器可以通过\" \"分割，实现对某种嵌套的元素中最内层的元素生效。";
        String s2 = "如果 .b {} 对class=\"b\"的元素不生效，可以通过改用这种嵌套的方式实现。";
        System.out.println(LCSUtil.longestCommonSubsequenceLength(s1, s2));
        System.out.println(LCSUtil.longestCommonSubsequence(s1, s2));
        s1 = "ABCBD";
        s2 = "ABD";
        System.out.println(LCSUtil.longestCommonSubsequenceLength(s1, s2));
        System.out.println(LCSUtil.longestCommonSubsequence(s1, s2));
    }

    @Test
    public void testDiff() {
        String s1 = "选择器可以通过\" \"分割，实现对某种嵌套的元素中最内层的元素生效。";
        String s2 = "如果 .b {} 对class=\"b\"的元素不生效，可以通过改用这种嵌套的方式实现。";
        String common = LCSUtil.longestCommonSubsequence(s1, s2);
        String[] diffs = LCSUtil.diff(s1, s2);
        System.out.println("文本1：" + s1);
        System.out.println("文本2：" + s2);
        System.out.println("公共部分：" + common);
        for (String diff : diffs) {
            System.out.println("差异部分：" + diff);
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入两行文本，获取最长公共子串、最长公共子序列、差异部分");
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        System.out.println();
        System.out.println("文本1：" + s1);
        System.out.println("文本2：" + s2);
        System.out.println();
        System.out.println("最长公共子串：" + LCSUtil.longestCommonSubstring(s1, s2));
        System.out.println("最长公共子序列：" + LCSUtil.longestCommonSubsequence(s1, s2));
        for (String diff : LCSUtil.diff(s1, s2)) {
            System.out.println("差异部分：" + diff);
        }
    }

}
