package cn.jinty.sql.validate;

import cn.jinty.util.string.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 表校验
 *
 * @author Jinty
 * @date 2023/8/29
 **/
@Data
@NoArgsConstructor
public class TableValidation {

    // 失败终止
    private Boolean terminateForFail;

    // 字段默认值
    private Map<String, Set<String>> defaultValue;

    /**
     * 从配置文件中解析
     *
     * @param props 配置文件
     * @return 表校验
     */
    public static TableValidation parseFromProps(Properties props) {
        TableValidation validation = new TableValidation();
        String terminateForFail = props.getProperty("validation.terminateForFail");
        validation.setTerminateForFail(Boolean.valueOf(terminateForFail));
        final String SEP = ",";
        String defaultValueColumns = props.getProperty("validation.defaultValue.columns");
        if (StringUtil.isNotBlank(defaultValueColumns)) {
            Map<String, Set<String>> defaultValueMap = new HashMap<>();
            for (String column : defaultValueColumns.split(SEP)) {
                String defaultValue = props.getProperty("validation.defaultValue." + column);
                if (StringUtil.isBlank(defaultValue)) {
                    continue;
                }
                defaultValueMap.put(column, new HashSet<>(Arrays.asList(defaultValue.split(","))));
            }
            validation.setDefaultValue(defaultValueMap);
        }
        return validation;
    }

}
