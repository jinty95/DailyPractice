package cn.jinty.entity.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数对、二元组
 *
 * @author jintai.wang
 * @date 2022/1/26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<L, R> {

    // 左
    private L left;

    // 右
    private R right;

}
