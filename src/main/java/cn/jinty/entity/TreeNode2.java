package cn.jinty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树型节点2 - 子节点关联父节点
 *
 * @author jintai.wang
 * @date 2022/4/11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode2 {

    // ID
    private Long id;

    // 名称
    private String name;

    // 层级
    private Long level;

    // 父节点ID
    private Long parentId;

    /**
     * 树型节点2 -> 树型节点1
     *
     * @param list2 树型节点2
     * @return 树型节点1
     */
    public static List<TreeNode1> convert(List<TreeNode2> list2) {
        List<TreeNode1> list1 = new ArrayList<>();
        if (list2 == null || list2.isEmpty()) {
            return list1;
        }
        // id -> 树型节点1
        Map<Long, TreeNode1> idToTreeNode1 = new HashMap<>();
        // id -> parentId
        Map<Long, Long> idToParentId = new HashMap<>();
        // 所有树型节点2转为树型节点1
        for (TreeNode2 node2 : list2) {
            TreeNode1 node1 = new TreeNode1(node2.getId(), node2.getName(), node2.getLevel(), new ArrayList<>());
            idToTreeNode1.put(node2.getId(), node1);
            idToParentId.put(node2.getId(), node2.getParentId());
        }
        // 遍历树型节点1，将其添加到其父节点中，如果没有父节点，则收集起来
        for (TreeNode1 node1 : idToTreeNode1.values()) {
            TreeNode1 parent = idToTreeNode1.get(idToParentId.get(node1.getId()));
            if (parent != null) {
                parent.getChildren().add(node1);
            } else {
                list1.add(node1);
            }
        }
        return list1;
    }

}
