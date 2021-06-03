package cn.jinty.struct.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 *
 * @author jinty
 * @date 2021/4/14
 **/
public class Trie {

    //内部类
    private static class TrieNode{
        //对应的字符是否为词的终点
        Boolean flag;
        //字符集及其后续
        Map<Character,TrieNode> data;
        //构造器
        TrieNode(Boolean flag,HashMap<Character,TrieNode> data){
            this.flag = flag;
            this.data = data;
        }
    }

    //成员变量
    private TrieNode trie;

    //构造器
    public Trie() {
        trie = new TrieNode(false, new HashMap<>());
    }

    //存入单词
    public void insert(String word) {
        //空串
        if(word==null || word.length()==0) return;
        //从根往下按字符逐个存入
        TrieNode node = this.trie;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            Map<Character,TrieNode> map = node.data;
            TrieNode nextNode = map.get(c);
            //不存在该字符，则创建节点
            if(nextNode==null){
                nextNode = new TrieNode(false, new HashMap<>());
                map.put(c,nextNode);
            }
            //指向下一个节点
            node = nextNode;
        }
        //单词末尾添加标识
        node.flag = true;
    }

    //查找单词
    public boolean search(String word) {
        //空串
        if(word==null || word.length()==0) return true;
        //从根往下逐个字符搜索
        TrieNode node = this.trie;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            Map<Character,TrieNode> map = node.data;
            TrieNode nextNode = map.get(c);
            //如果节点不存在，返回false
            if(nextNode==null) return false;
            node = nextNode;
        }
        //返回该节点是否为单词末尾
        return node.flag;
    }

    //是否存在前缀
    public boolean startsWith(String prefix) {
        //空串
        if(prefix==null || prefix.length()==0) return true;
        //从根往下逐个字符搜索
        TrieNode node = this.trie;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            Map<Character,TrieNode> map = node.data;
            TrieNode nextNode = map.get(c);
            //如果节点不存在，返回false
            if(nextNode==null) return false;
            node = nextNode;
        }
        return true;
    }

}
