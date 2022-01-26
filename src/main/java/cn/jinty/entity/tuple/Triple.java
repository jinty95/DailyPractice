package cn.jinty.entity.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 三元组
 *
 * @author jintai.wang
 * @date 2022/1/26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Triple<L, M, R> {

    // 左
    private L left;

    // 中
    private M middle;

    // 右
    private R right;

}
