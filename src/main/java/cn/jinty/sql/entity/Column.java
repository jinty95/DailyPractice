package cn.jinty.sql.entity;

import lombok.Data;

/**
 * 字段
 *
 * @author Jinty
 * @date 2023/3/4
 */
@Data
public class Column {

    // 名称
    private String name;

    // 类型
    private String type;

    // 长度
    private String length;

    // 是否允许为空 (注意：如果用boolean类型，get/set方法分别是isNullable/setNullable)
    private Boolean isNullable = true;

    // 默认值
    private String defaultValue;

    // 说明
    private String comment;

    // 是否主键
    private Boolean isPrimaryKey = false;

}
