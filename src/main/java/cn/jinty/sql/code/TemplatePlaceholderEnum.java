package cn.jinty.sql.code;

import java.util.Arrays;
import java.util.List;

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
    // 末端包名
    END_PACKAGE_ENTITY,
    END_PACKAGE_XML,
    END_PACKAGE_MAPPER,
    END_PACKAGE_SERVICE,
    END_PACKAGE_SERVICE_IMPL,
    END_PACKAGE_XML_EXT,
    END_PACKAGE_MAPPER_EXT,
    END_PACKAGE_ENTITY_FOR_MP,

    // 类
    CLASS_NAME,
    // 末端名称
    END_NAME_ENTITY,
    END_NAME_XML,
    END_NAME_MAPPER,
    END_NAME_SERVICE,
    END_NAME_SERVICE_IMPL,
    END_NAME_XML_EXT,
    END_NAME_MAPPER_EXT,
    END_NAME_ENTITY_FOR_MP,

    // 类字段
    FIELD_CLASS,
    FIELD_TYPE,
    FIELD_NAME,
    FIELD_NAME_UPPER_FIRST,

    // 表
    TABLE_NAME,
    TABLE_COMMENT,

    // 表字段
    COLUMN_NAME,
    COLUMN_TYPE,
    COLUMN_DEFAULT,
    COLUMN_COMMENT,

    // 主键
    PK_FIELD_CLASS,
    PK_FIELD_TYPE,
    PK_FIELD_NAME,
    PK_FIELD_NAME_UPPER_FIRST,
    PK_COLUMN_NAME,
    PK_COLUMN_TYPE,
    PK_COLUMN_COMMENT,

    // 遍历(字段)
    FOR_EACH,
    END_FOR_EACH,

    ;

    // 类字段、表字段
    public static List<TemplatePlaceholderEnum> fieldAndColumn() {
        return Arrays.asList(
                FIELD_CLASS, FIELD_TYPE, FIELD_NAME, FIELD_NAME_UPPER_FIRST,
                COLUMN_NAME, COLUMN_TYPE, COLUMN_DEFAULT, COLUMN_COMMENT
        );
    }

}
