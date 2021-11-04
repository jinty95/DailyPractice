package test.cn.jinty.struct.graph;

import cn.jinty.struct.graph.GraphNode;
import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.List;

/**
 * 图 - 测试
 *
 * @author Jinty
 * @date 2021/6/3
 **/
public class GraphTest {

    //建立一个无向连通图
    private GraphNode buildGraph() {
        //节点
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        //邻居
        List<GraphNode> n1 = ListUtil.asList(node2, node3);
        List<GraphNode> n2 = ListUtil.asList(node1, node3, node4);
        List<GraphNode> n3 = ListUtil.asList(node1, node2, node4);
        List<GraphNode> n4 = ListUtil.asList(node2, node3);
        node1.neighbors = n1;
        node2.neighbors = n2;
        node3.neighbors = n3;
        node4.neighbors = n4;
        return node1;
    }

    @Test
    public void testCloneGraph() {
        GraphNode graph = buildGraph();
        System.out.println(graph);
        GraphNode clone = graph.cloneGraph();
        System.out.println(clone);
    }

}
