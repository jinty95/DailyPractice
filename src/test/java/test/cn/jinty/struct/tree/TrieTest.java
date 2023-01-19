package test.cn.jinty.struct.tree;

import cn.jinty.Main;
import cn.jinty.struct.tree.Trie;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 前缀树 - 测试
 *
 * @author Jinty
 * @date 2023/1/19
 **/
public class TrieTest {

    // 词库
    private static final List<String> words;
    static {
        words = new ArrayList<>();
        URL url = Main.class.getResource("/txt/sensitive_word.txt");
        assert url != null;
        File file = new File(url.getPath());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String word = br.readLine();
            while (word != null) {
                words.add(word);
                word = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 前缀树
    private static final Trie trie;
    static {
        trie = new Trie();
        for (String word : words) {
            if (word == null || word.length() == 0) {
                continue;
            }
            trie.insert(word);
        }
    }

    // 搜索
    @Test
    public void testInsertAndSearch() {
        for (String word : words) {
            System.out.printf("字典存在单词[%s]：%s%n", word, trie.search(word));
        }
        System.out.printf("字典存在单词前缀[%s]：%s%n", "你", trie.searchPrefix("你"));
        System.out.printf("字典存在单词[%s]：%s%n", "哎呀", trie.search("哎呀"));
        System.out.printf("字典存在单词[%s]：%s%n", "你..", trie.searchWithPoint("你.."));
    }

    // 搜索并替换敏感词
    @Test
    public void testSearchAllAndReplace() {
        String text = "第一次呀变成这样的我，不管我怎么去否认，哎哟！你干嘛，只因你太美，Baby，啊啊啊啊";
        System.out.println("原始输入文本：" + text);
        System.out.println("去除敏感词后：" + trie.searchAllAndReplace(text, '*'));
    }

    public static void main(String[] args) {
        System.out.println("请输入一行文字，帮您过滤一下敏感词：");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String filterText = trie.searchAllAndReplace(text, '*');
        System.out.println();
        System.out.println("原始输入文本：" + text);
        System.out.println("去除敏感词后：" + filterText);
        System.out.println();
        if (filterText.equals(text)) {
            System.out.println("恭喜您！没有敏感词，可以发布。");
        } else {
            System.out.println("很遗憾！请修改后再发布。");
        }
    }

}
