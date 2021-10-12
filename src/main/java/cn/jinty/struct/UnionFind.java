package cn.jinty.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集
 *
 * @author jinty
 * @date 2021/2/26
 **/
public class UnionFind {

    //一个节点关联它所属集合的父节点
    public Map<String, String> parents;
    //节点的秩(顶层节点的秩 = 节点的秩 + 所有子节点的秩)
    public Map<String, Integer> number;

    //构造器
    public UnionFind() {
        parents = new HashMap<>();
        number = new HashMap<>();
    }

    //寻找i所属集合的顶层节点
    public String find(String i) {
        if (!parents.get(i).equals(i)) {
            //路径压缩
            parents.put(i, find(parents.get(i)));
        }
        return parents.get(i);
    }

    //合并两个节点所属集合(合并这两个节点所属集合的顶层节点，两个顶层节点形成丛属关系，则两个集合完成合并)
    public void union(String i, String j) {
        String pi = find(i);
        String pj = find(j);
        if (pi.equals(pj)) return;
        //字典序小的作为根
        if (pi.compareTo(pj) < 0) {
            parents.put(pj, pi);
            number.put(pi, number.get(pj) + number.get(pi));
        } else {
            parents.put(pi, pj);
            number.put(pj, number.get(pi) + number.get(pj));
        }
    }

    //查i对应的顶层节点的秩
    public int getNumber(String i) {
        return number.get(find(i));
    }

}
