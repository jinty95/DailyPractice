package cn.jinty.struct.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 *
 * @author jinty
 * @date 2021/4/14
 **/
@SuppressWarnings("unused")
public class Trie {

    //内部类
    public static class TrieNode {
        //对应的字符是否为词的终点
        public Boolean flag;
        //字符集及其后续
        private Map<Character, TrieNode> data;

        //构造器
        private TrieNode(Boolean flag, HashMap<Character, TrieNode> data) {
            this.flag = flag;
            this.data = data;
        }
    }

    //成员变量
    private TrieNode trieNode;

    //构造器
    public Trie() {
        trieNode = new TrieNode(false, new HashMap<>());
    }

    //存入单词
    public void insert(String word) {
        //空串
        if (word == null || word.length() == 0) return;
        //从根往下按字符逐个存入
        TrieNode node = this.trieNode;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character, TrieNode> map = node.data;
            node = map.computeIfAbsent(c, k -> new TrieNode(false, new HashMap<>()));
        }
        //单词末尾添加标识
        node.flag = true;
    }

    //查找单词
    public boolean search(String word) {
        TrieNode node = find(word);
        if (node == null) return false;
        return node.flag;
    }

    //查找前缀
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    //查找单词，返回最后一个节点，如果不能匹配，返回null
    public TrieNode find(String word) {
        TrieNode node = this.trieNode;
        //空串
        if (word == null || word.length() == 0) return node;
        //从根往下逐个字符搜索
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character, TrieNode> map = node.data;
            TrieNode nextNode = map.get(c);
            //不能匹配，返回null
            if (nextNode == null) return null;
            node = nextNode;
        }
        //返回最后一个节点
        return node;
    }

}
