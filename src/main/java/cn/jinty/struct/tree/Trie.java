package cn.jinty.struct.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前缀树
 *
 * @author Jinty
 * @date 2021/4/14
 **/
public class Trie {

    // 内部类 - 前缀树节点
    public static class TrieNode {

        // 对应的字符是否为词的终点
        public boolean flag;

        // 字符集及其后续
        public Map<Character, TrieNode> data;

        // 构造器
        private TrieNode(boolean flag, HashMap<Character, TrieNode> data) {
            this.flag = flag;
            this.data = data;
        }

    }

    // 成员变量 - 前缀树根节点
    private final TrieNode trieNode;

    // 构造器
    public Trie() {
        trieNode = new TrieNode(false, new HashMap<>());
    }

    // 获取前缀树根节点
    public TrieNode root() {
        return this.trieNode;
    }

    /**
     * 向前缀树中插入单词
     *
     * @param word 单词
     */
    public void insert(String word) {
        // 空串
        if (word == null || word.length() == 0) return;
        // 从根往下按字符逐个存入
        TrieNode node = root();
        for (int i = 0; i < word.length(); i++) {
            node = node.data.computeIfAbsent(word.charAt(i), k -> new TrieNode(false, new HashMap<>()));
        }
        // 单词末尾添加标识
        node.flag = true;
    }

    /**
     * 查找前缀树中是否存在给定单词
     *
     * @param word 单词
     * @return 是否
     */
    public boolean search(String word) {
        TrieNode node = find(word);
        if (node == null) return false;
        return node.flag;
    }

    /**
     * 查询前缀树中是否存在给定前缀
     *
     * @param prefix 前缀
     * @return 是否
     */
    public boolean searchPrefix(String prefix) {
        return find(prefix) != null;
    }

    /**
     * 查找前缀树中是否存在给定单词，单词中的'.'可以匹配任何字符
     *
     * @param word 单词
     * @return 是否
     */
    public boolean searchWithPoint(String word) {
        return searchWithPoint(word, 0, root());
    }

    private boolean searchWithPoint(String word, int idx, TrieNode node) {
        if (word == null || idx == word.length()) {
            return node != null && node.flag;
        }
        if (node == null) {
            return false;
        }
        char c = word.charAt(idx);
        if (c == '.') {
            // '.'模糊匹配
            for (TrieNode oneNode : node.data.values()) {
                if (searchWithPoint(word, idx + 1, oneNode)) {
                    return true;
                }
            }
            return false;
        } else {
            // 其余字符精确匹配
            return searchWithPoint(word, idx + 1, node.data.get(c));
        }
    }

    /**
     * 查找前缀树中是否存在给定单词
     *
     * @param word 单词
     * @return 最后一个节点或者null
     */
    public TrieNode find(String word) {
        TrieNode node = root();
        // 空串
        if (word == null || word.length() == 0) return node;
        // 从根往下逐个字符搜索
        for (int i = 0; i < word.length(); i++) {
            node = node.data.get(word.charAt(i));
            // 不能匹配，返回null
            if (node == null) {
                return null;
            }
        }
        // 返回最后一个节点
        return node;
    }

    /**
     * 检查文本是否存在前缀树中的单词
     *
     * @param text 文本
     * @return 是否
     */
    public boolean searchAll(String text) {
        // 空串
        if (text == null || text.length() == 0) {
            return false;
        }
        char[] chars = text.toCharArray();
        Trie.TrieNode node = root();
        // 定位每轮第一个匹配到的字符位置
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            node = node.data.get(chars[i]);
            // 单词匹配失败，从本轮第一个匹配到的字符所在位置的下一个位置开始新一轮搜索
            if (node == null) {
                node = root();
                i = start++;
                continue;
            }
            // 单词匹配成功，直接返回
            if (node.flag) {
                return true;
            }
            // 单词匹配中，继续本轮搜索
        }
        return false;
    }

    /**
     * 在给定文本中搜索前缀树中的所有单词，并列举出来
     *
     * @param text 文本
     * @return 所有匹配单词
     */
    public List<String> searchAllAndList(String text) {
        List<String> words = new ArrayList<>();
        // 空串
        if (text == null || text.length() == 0) {
            return words;
        }
        char[] chars = text.toCharArray();
        Trie.TrieNode node = root();
        // 定位每轮第一个匹配到的字符位置
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            node = node.data.get(chars[i]);
            // 单词匹配失败，从本轮第一个匹配到的字符所在位置的下一个位置开始新一轮搜索
            if (node == null) {
                node = root();
                i = start++;
                continue;
            }
            // 单词匹配成功，收集起来，从当前位置的下一个位置开始新一轮搜索
            if (node.flag) {
                StringBuilder word = new StringBuilder();
                for (int j = start; j <= i; j++) {
                    word.append(chars[j]);
                }
                words.add(word.toString());
                node = root();
                start = i + 1;
            }
            // 单词匹配中，继续本轮搜索
        }
        return words;
    }

    /**
     * 在给定文本中搜索前缀树中的所有单词，替换为给定字符
     *
     * @param text    文本
     * @param replace 替换字符
     * @return 处理后文本
     */
    public String searchAllAndReplace(String text, char replace) {
        // 空串
        if (text == null || text.length() == 0) {
            return text;
        }
        char[] chars = text.toCharArray();
        Trie.TrieNode node = root();
        // 定位每轮第一个匹配到的字符位置
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            node = node.data.get(chars[i]);
            // 单词匹配失败，从本轮第一个匹配到的字符所在位置的下一个位置开始新一轮搜索
            if (node == null) {
                node = root();
                i = start++;
                continue;
            }
            // 单词匹配成功，将本轮第一个匹配到的字符所在位置到当前位置的所有字符都替换掉，从当前位置的下一个位置开始新一轮搜索
            if (node.flag) {
                for (int j = start; j <= i; j++) {
                    chars[j] = replace;
                }
                node = root();
                start = i + 1;
            }
            // 单词匹配中，继续本轮搜索
        }
        return new String(chars);
    }

}
