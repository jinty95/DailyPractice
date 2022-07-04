package cn.jinty.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集
 *
 * @author Jinty
 * @date 2021/2/26
 **/
public class UnionFind {

    // 节点 -> 父节点(或顶层父节点)
    public Map<String, String> parents;
    // 节点 -> 秩(节点的值与所有子节点的值的总和)
    public Map<String, Integer> number;

    // 构造器
    public UnionFind() {
        parents = new HashMap<>();
        number = new HashMap<>();
    }

    /**
     * 寻找节点对应的顶层父节点
     *
     * @param node 节点
     * @return 顶层父节点
     */
    public String find(String node) {
        if (!parents.get(node).equals(node)) {
            // 路径压缩(使得每个节点都直接关联顶层父节点)
            parents.put(node, find(parents.get(node)));
        }
        return parents.get(node);
    }

    /**
     * 合并两个节点所属集合(合并这两个节点所属集合的顶层父节点，两个顶层父节点形成丛属关系，则两个集合完成合并)
     *
     * @param node1 节点1
     * @param node2 节点2
     */
    public void union(String node1, String node2) {
        String parent1 = find(node1);
        String parent2 = find(node2);
        // 从属一个集合，不需要合并
        if (parent1.equals(parent2)) return;
        // 字典序小的作为顶层父节点
        if (parent1.compareTo(parent2) < 0) {
            parents.put(parent2, parent1);
            number.put(parent1, number.get(parent2) + number.get(parent1));
        } else {
            parents.put(parent1, parent2);
            number.put(parent2, number.get(parent1) + number.get(parent2));
        }
    }

    /**
     * 获取节点所属集合的秩(即顶层父节点的秩)
     *
     * @param node 节点
     * @return 所属集合的秩
     */
    public Integer getNumber(String node) {
        return number.get(find(node));
    }

}
