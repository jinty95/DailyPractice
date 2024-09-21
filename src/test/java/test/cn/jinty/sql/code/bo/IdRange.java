package test.cn.jinty.sql.code.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ID范围
 *
 * @author Jinty
 * @date 2023/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdRange {

    // 最小ID
    private Long minId;

    // 最大ID
    private Long maxId;

}
