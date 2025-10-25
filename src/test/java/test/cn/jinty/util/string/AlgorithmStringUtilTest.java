package test.cn.jinty.util.string;

import cn.jinty.util.string.AlgorithmStringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 算法 - 字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class AlgorithmStringUtilTest {

    @Test
    public void testLongestCommonSubstring() {
        String s1 = "Hello";
        String s2 = "He is very well";
        System.out.println(AlgorithmStringUtil.longestCommonSubstringLength(s1, s2));
        System.out.println(AlgorithmStringUtil.longestCommonSubstring(s1, s2));
        s1 = "I am very well now";
        s2 = "He is very well too";
        System.out.println(AlgorithmStringUtil.longestCommonSubstringLength(s1, s2));
        System.out.println(AlgorithmStringUtil.longestCommonSubstring(s1, s2));
    }

    @Test
    public void testlongestCommonSubsequence() {
        String s1 = "选择器可以通过\" \"分割，实现对某种嵌套的元素中最内层的元素生效。";
        String s2 = "如果 .b {} 对class=\"b\"的元素不生效，可以通过改用这种嵌套的方式实现。";
        System.out.println(AlgorithmStringUtil.longestCommonSubsequenceLength(s1, s2));
        System.out.println(AlgorithmStringUtil.longestCommonSubsequence(s1, s2));
        s1 = "ABCBD";
        s2 = "ABD";
        System.out.println(AlgorithmStringUtil.longestCommonSubsequenceLength(s1, s2));
        System.out.println(AlgorithmStringUtil.longestCommonSubsequence(s1, s2));
    }

    @Test
    public void testDiff() {
        String s1 = "选择器可以通过\" \"分割，实现对某种嵌套的元素中最内层的元素生效。";
        String s2 = "如果 .b {} 对class=\"b\"的元素不生效，可以通过改用这种嵌套的方式实现。";
        String common = AlgorithmStringUtil.longestCommonSubsequence(s1, s2);
        String[] diffs = AlgorithmStringUtil.diff(s1, s2);
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
        System.out.println("最长公共子串：" + AlgorithmStringUtil.longestCommonSubstring(s1, s2));
        System.out.println("最长公共子序列：" + AlgorithmStringUtil.longestCommonSubsequence(s1, s2));
        System.out.println();
        for (String diff : AlgorithmStringUtil.diff(s1, s2)) {
            System.out.println("差异部分：" + diff);
        }
    }

    @Test
    public void testMinDistance() {
        String[] arr1 = {"horse", "intention", "git push"};
        String[] arr2 = {"ros", "execution", "git pull"};
        int[] disArr = {3, 5, 2};
        for (int i = 0; i < arr1.length; i++) {
            int dis = AlgorithmStringUtil.minDistance(arr1[i], arr2[i]);
            System.out.printf("字符串[%s]与字符串[%s]的最小编辑距离为[%s]%n", arr1[i], arr2[i], dis);
            Assert.assertEquals(dis, disArr[i]);
        }
    }

    @Test
    public void testMinDistanceApply() {
        Set<String> gitCmdSet = new HashSet<>(Arrays.asList("git add", "git commit", "git push", "git pull"));
        String cmd = "gitadd";
        System.out.println("输入GIT命令：" + cmd);
        if (gitCmdSet.contains(cmd)) {
            System.out.println("命令正确");
        } else {
            int minDis = Integer.MAX_VALUE;
            String possibleCmd = "";
            for (String gitCmd : gitCmdSet) {
                int dis = AlgorithmStringUtil.minDistance(cmd, gitCmd);
                if (dis < minDis) {
                    minDis = dis;
                    possibleCmd = gitCmd;
                }
            }
            System.out.println("命令错误，您可能想要输入的是：" + possibleCmd);
        }
    }

}
