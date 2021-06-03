package cn.jinty.struct.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 图 - 节点
 *
 * @author jinty
 * @date 2021/6/3
 **/
public class GraphNode {

    //节点值
    public int val;
    //邻居节点
    public List<GraphNode> neighbors;

    //构造器
    public GraphNode(int val){
        this.val = val;
        this.neighbors = new ArrayList<>();
    }
    public GraphNode(int val, List<GraphNode> neighbors){
        this.val = val;
        this.neighbors = neighbors;
    }

    //深度优先遍历
    public List<GraphNode> dfs(){
        List<GraphNode> list = new ArrayList<>();
        Set<GraphNode> set = new HashSet<>();
        dfs(this,set,list);
        return list;
    }
    private void dfs(GraphNode node, Set<GraphNode> set, List<GraphNode> list){
        //一个节点只处理一次
        if(set.contains(node)) return;
        set.add(node);
        list.add(node);
        //枚举邻居节点，递归
        for(GraphNode n : node.neighbors){
            dfs(n,set,list);
        }
    }

    //广度优先遍历
    public List<GraphNode> bfs(){
        List<GraphNode> list = new ArrayList<>();
        Set<GraphNode> set = new HashSet<>();
        bfs(this,set,list);
        return list;
    }
    private void bfs(GraphNode node, Set<GraphNode> set, List<GraphNode> list){
        //使用队列实现
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        while( ! queue.isEmpty()){
            GraphNode n = queue.poll();
            //一个节点只处理一次
            if(set.contains(n)) continue;
            set.add(n);
            list.add(n);
            //枚举邻居节点，入队列
            for(GraphNode neighbor : n.neighbors){
                queue.offer(neighbor);
            }
        }
    }

    @Override
    public String toString() {
        return bfs().stream()
                .map(a->a.val)
                .collect(Collectors.toList())
                .toString();
    }

    //克隆
    public GraphNode cloneGraph(){
        //原节点列表
        List<GraphNode> oldList = dfs();
        //新节点列表
        List<GraphNode> newList = new ArrayList<>();
        //新旧节点映射
        Map<GraphNode,GraphNode> oldToNew = new HashMap<>();
        //复制值
        for(GraphNode old : oldList){
            GraphNode newNode = new GraphNode(old.val);
            newList.add(newNode);
            oldToNew.put(old,newNode);
        }
        //复制邻居
        for(int i=0;i<oldList.size();i++){
            List<GraphNode> newNeighbors = new ArrayList<>();
            for(GraphNode oldNeighbor : oldList.get(i).neighbors){
                newNeighbors.add(oldToNew.get(oldNeighbor));
            }
            newList.get(i).neighbors = newNeighbors;
        }
        return newList.get(0);
    }

}
