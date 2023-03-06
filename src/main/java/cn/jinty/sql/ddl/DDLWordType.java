package cn.jinty.sql.ddl;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DDL单词类型
 *
 * @author Jinty
 * @date 2023/3/4
 */
@Getter
@AllArgsConstructor
public enum DDLWordType {

    INIT("初始"),

    TABLE_NAME("表名"),
    TABLE_COMMENT("表说明"),

    FIELD_NAME("字段名称"),
    FIELD_TYPE("字段类型"),
    FIELD_LENGTH("字段长度"),
    FIELD_NULLABLE("字段可空"),
    FIELD_DEFAULT("字段默认值"),
    FIELD_COMMENT("字段说明"),

    PRIMARY_KEY("主键索引"),
    INDEX("普通索引"),
    UNIQUE("唯一索引");

    // 描述
    private final String desc;

    /**
     * 是否属于字段
     *
     * @param wordType 单词类型
     * @return 是否
     */
    public static boolean isField(DDLWordType wordType) {
        return wordType == FIELD_NAME || wordType == FIELD_TYPE || wordType == FIELD_LENGTH
                || wordType == FIELD_NULLABLE || wordType == FIELD_DEFAULT || wordType == FIELD_COMMENT;
    }

    /**
     * 是否属于索引
     *
     * @param wordType 单词类型
     * @return 是否
     */
    public static boolean isIndex(DDLWordType wordType) {
        return wordType == PRIMARY_KEY || wordType == INDEX || wordType == UNIQUE;
    }

}
