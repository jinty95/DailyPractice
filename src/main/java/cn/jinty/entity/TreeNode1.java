package cn.jinty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 树型节点1 - 父节点关联子节点
 *
 * @author jintai.wang
 * @date 2022/4/11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode1 {

    // ID
    private Long id;

    // 名称
    private String name;

    // 层级
    private Long level;

    // 子节点
    private List<TreeNode1> children;

}
