package cn.jinty.sql.code;

/**
 * 模板占位符 - 枚举
 * (数据库字段为COLUMN，实体类字段为FIELD)
 *
 * @author Jinty
 * @date 2023/3/24
 **/
public enum TemplatePlaceholderEnum {

    // 基本信息
    AUTHOR,
    DATE,

    // 导入类
    IMPORT_CLASS,

    // 包
    BASE_PACKAGE,

    // 类
    CLASS_NAME,
    CLASS_FIELDS,

    // 表
    TABLE_NAME,
    TABLE_COMMENT,
    TABLE_COLUMNS,

    // 主键
    PK_FIELD_CLASS,
    PK_FIELD_TYPE,
    PK_FIELD_NAME,
    PK_COLUMN_NAME,

    // SQL元素
    SQL_RESULT_MAP,
    SQL_SELECT_CONDITION,
    SQL_UPDATE_COLUMNS,
    SQL_INSERT_GEN_KEY,
    SQL_INSERT_DEFAULT_COLUMNS,
    SQL_INSERT_DEFAULT_VALUES,
    SQL_INSERT_COLUMNS,
    SQL_INSERT_VALUES,
    SQL_BATCH_INSERT_COLUMNS,
    SQL_BATCH_INSERT_VALUES,

}
