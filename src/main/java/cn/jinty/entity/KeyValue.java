package cn.jinty.entity;

/**
 * 键值对 - 实体类
 *
 * @author Jinty
 * @date 2021/12/7
 */
public class KeyValue<K,V> {

    private K key;

    private V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
