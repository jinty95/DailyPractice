package cn.jinty.sql.entity;

import lombok.Data;

import java.util.List;

/**
 * 表
 *
 * @author Jinty
 * @date 2023/3/4
 */
@Data
public class Table {

    // 名称
    private String name;

    // 说明
    private String comment;

    // 字段列表
    private List<Column> columns;

}
