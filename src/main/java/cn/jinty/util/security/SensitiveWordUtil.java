package cn.jinty.util.security;

import cn.jinty.struct.tree.Trie;
import cn.jinty.util.io.FilePathUtil;
import cn.jinty.util.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词 - 工具类
 *
 * @author Jinty
 * @date 2023/9/8
 **/
public class SensitiveWordUtil {

    private SensitiveWordUtil() {
    }

    // 词库
    private static final List<String> words;

    // 载入词库
    static {
        words = new ArrayList<>();
        File file = new File(FilePathUtil.getAbsolutePath("/txt/sensitive_word.txt", true));
        try {
            words.addAll(FileUtil.readLine(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 前缀树
    private static final Trie trie;

    // 基于词库构建前缀树
    static {
        trie = new Trie();
        for (String word : words) {
            if (word == null || word.isEmpty()) {
                continue;
            }
            trie.insert(word);
        }
    }

    /**
     * 检查单词是否为敏感词
     *
     * @param word 单词
     * @return 是否
     */
    public static boolean isSensitive(String word) {
        return trie.search(word);
    }

    /**
     * 检查文本是否包含敏感词
     *
     * @param text 文本
     * @return 是否
     */
    public static boolean containsSensitive(String text) {
        return trie.searchAll(text);
    }

    /**
     * 列举出文本中所有的敏感词
     *
     * @param text 文本
     * @return 所有的敏感词
     */
    public static List<String> listSensitive(String text) {
        return trie.searchAllAndList(text);
    }

    /**
     * 将文本中的所有敏感词替换为指定字符
     *
     * @param text    文本
     * @param replace 脱敏字符
     * @return 脱敏文本
     */
    public static String replaceSensitive(String text, char replace) {
        return trie.searchAllAndReplace(text, replace);
    }

}
