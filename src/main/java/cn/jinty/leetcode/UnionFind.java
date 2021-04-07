package cn.jinty.leetcode;

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
    public Map<String,String> parents;
    //顶层节点对应的秩(节点本身+所有子节点)
    public Map<String,Integer> number;

    public UnionFind(){
        parents = new HashMap<>();
        number = new HashMap<>();
    }

    //寻找x所属集合的顶层节点
    public String find(String x){
        if(!parents.get(x).equals(x)){
            //路径压缩
            parents.put(x,find(parents.get(x)));
        }
        return parents.get(x);
    }

    //合并两个节点所属集合(合并这两个节点所属集合的顶层节点，两个顶层节点形成丛属关系，则两个集合完成合并)
    public void union(String x,String y){
        String px = find(x);
        String py = find(y);
        if(px.equals(py)) return;
        //字典序小的作为根
        if(px.compareTo(py) < 0){
            parents.put(py,px);
            number.put(px,number.get(py)+number.get(px));
        }else{
            parents.put(px,py);
            number.put(py,number.get(px)+number.get(py));
        }
    }

    //查x对应的顶层节点的秩
    public int getNumber(String x){
        return number.get(find(x));
    }

}
