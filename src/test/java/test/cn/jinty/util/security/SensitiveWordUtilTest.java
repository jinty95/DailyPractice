package test.cn.jinty.util.security;

import cn.jinty.util.security.SensitiveWordUtil;
import org.junit.Test;

import java.util.Scanner;

/**
 * 敏感词 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/9/8
 **/
public class SensitiveWordUtilTest {

    private String getText() {
        return "第一次呀变成这样的我 " +
                "不管我怎么去否认 " +
                "只因你太美 baby 只因你太美 baby " +
                "只因你实在是太美 baby " +
                "只因你太美 baby " +
                "Oh eh oh";
    }

    @Test
    public void testIsSensitive() {
        String[] words = {"哎哟", "你干嘛", "真的吗"};
        for (String word : words) {
            System.out.printf("单词[%s]是敏感词[%s]%n", word, SensitiveWordUtil.isSensitive(word));
        }
    }

    @Test
    public void testReplaceSensitive() {
        String text = getText();
        System.out.println("原文本：" + text);
        System.out.println("包含敏感词：" + SensitiveWordUtil.containsSensitive(text));
        System.out.println("敏感词列表：" + SensitiveWordUtil.listSensitive(text));
        System.out.println("脱敏文本：" + SensitiveWordUtil.replaceSensitive(text, '*'));
    }

    public static void main(String[] args) {
        System.out.println("请输入一行文字，帮您过滤一下敏感词：");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String filterText = SensitiveWordUtil.replaceSensitive(text, '*');
        System.out.println();
        System.out.println("原文本：" + text);
        System.out.println("脱敏文本：" + filterText);
        System.out.println();
        if (filterText.equals(text)) {
            System.out.println("恭喜您！没有敏感词，可以发布。");
        } else {
            System.out.println("很遗憾！请修改后再发布。");
        }
    }

}
