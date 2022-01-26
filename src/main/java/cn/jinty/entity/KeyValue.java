package cn.jinty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 键值对
 *
 * @author Jinty
 * @date 2021/12/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K,V> {

    // 键
    private K key;

    // 值
    private V value;

}
