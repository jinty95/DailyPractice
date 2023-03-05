package cn.jinty.sql.ddl;

import lombok.Data;

/**
 * DDL单词
 *
 * @author Jinty
 * @date 2023/3/4
 */
@Data
public class DDLWord {

    // 类型
    private DDLWordType type = DDLWordType.INIT;

    // 文本
    private StringBuilder text = new StringBuilder();

    // 字段序号
    private int fieldOrder = 0;

    public DDLWord() {
    }

    public DDLWord(int fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    /**
     * 追加字符
     *
     * @param c 字符
     */
    public void append(char c) {
        this.text.append(c);
    }

    /**
     * 追加字符
     *
     * @param arr   字符数组
     * @param begin 起始位置
     * @param count 字符数量
     */
    public void append(char[] arr, int begin, int count) {
        for (int i = begin; i < begin + count && i < arr.length; i++) {
            this.text.append(arr[i]);
        }
    }

    /**
     * 文本是否为空
     *
     * @return 是否
     */
    public boolean isEmpty() {
        return this.text.length() == 0;
    }

}
