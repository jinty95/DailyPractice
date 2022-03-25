package cn.jinty.entity.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一元组
 *
 * @author Jinty
 * @date 2022/3/25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Single<V> {

    private V value;

}
