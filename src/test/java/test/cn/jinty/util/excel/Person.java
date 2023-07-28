package test.cn.jinty.util.excel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * 个人信息
 *
 * @author Jinty
 * @date 2023/7/13
 **/
@Data
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private String idCard;
    private String phoneNum;
    private String remark;

    public static List<String> getTitles() {
        return Arrays.asList("ID", "名称", "年龄", "身份证号码", "手机号码", "备注");
    }

    public static List<String> getFields() {
        return Arrays.asList("id", "name", "age", "idCard", "phoneNum", "remark");
    }

    // 标题和字段建议写到配置里面，这样如果运行时需求变更，直接修改模板和配置就可以了，不用改代码重新部署
}
