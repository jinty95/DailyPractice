package cn.jinty.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置
 * 
 * @author Jinty
 * @date 2023/1/17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {

    // 类型
    private String type;

    // 键
    private String key;

    // 值
    private String value;

    // 说明
    private String description;

    // 备注
    private String remark;

}
