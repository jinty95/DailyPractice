package cn.jinty.sql.code;

/**
 * MybatisXml模板 - 占位符 - 枚举
 *
 * @author Jinty
 * @date 2023/3/21
 **/
public enum MybatisXmlTemplatePlaceholderEnum {

    // Mapper包名、类名、全名
    MAPPER_PACKAGE_NAME,
    MAPPER_CLASS_NAME,
    MAPPER_FULL_NAME,

    // 实体类包名、类名、全名
    ENTITY_PACKAGE_NAME,
    ENTITY_CLASS_NAME,
    ENTITY_FULL_NAME,

    // 表名
    TABLE_NAME,

    // 查询结果映射
    RESULT_MAP,

    // 表的所有字段
    COLUMNS,

    // 查询条件
    SELECT_CONDITION,

    // 插入语句
    INSERT_GEN_KEY,
    INSERT_COLUMNS,
    INSERT_VALUES,
    BATCH_INSERT_COLUMNS,
    BATCH_INSERT_VALUES,

    // 更新语句
    UPDATE_COLUMNS,

    // 主键
    PK_PARAM_TYPE,
    PK_CONDITION,

}
