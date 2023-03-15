package cn.jinty.sql.ddl;

import cn.jinty.sql.entity.Column;
import cn.jinty.sql.entity.Table;
import cn.jinty.util.collection.CharArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static cn.jinty.sql.ddl.DDLConstant.*;

/**
 * DDL解析器
 *
 * @author Jinty
 * @date 2023/3/5
 */
public class DDLParser {

    /**
     * DDL解析
     *
     * @param ddl DDL
     * @return 表对象
     */
    public static Table parse(String ddl) {
        Table table = new Table();
        Map<Integer, Column> columnMap = new TreeMap<>();
        List<DDLWord> words = parseToWord(ddl);
        for (DDLWord word : words) {
            if (word.getType() == DDLWordType.TABLE_NAME) {
                table.setName(word.getText().toString());
            } else if (word.getType() == DDLWordType.TABLE_COMMENT) {
                table.setComment(word.getText().toString());
            } else if (DDLWordType.isField(word.getType())) {
                Column column = columnMap.computeIfAbsent(word.getFieldOrder(), a -> new Column());
                if (word.getType() == DDLWordType.FIELD_NAME) {
                    column.setName(word.getText().toString());
                } else if (word.getType() == DDLWordType.FIELD_TYPE) {
                    column.setType(word.getText().toString());
                } else if (word.getType() == DDLWordType.FIELD_LENGTH) {
                    column.setLength(word.getText().toString());
                } else if (word.getType() == DDLWordType.FIELD_NULLABLE) {
                    if (NOT_NULL.equalsIgnoreCase(word.getText().toString())) {
                        column.setIsNullable(false);
                    } else {
                        column.setIsNullable(true);
                    }
                } else if (word.getType() == DDLWordType.FIELD_DEFAULT) {
                    column.setDefaultValue(word.getText().toString());
                } else if (word.getType() == DDLWordType.FIELD_COMMENT) {
                    column.setComment(word.getText().toString());
                }
            } else if (word.getType() == DDLWordType.PRIMARY_KEY) {
                for (Column column : columnMap.values()) {
                    if (column.getName().equalsIgnoreCase(word.getText().toString())) {
                        column.setIsPrimaryKey(true);
                    }
                }
            }
        }
        table.setColumns(new ArrayList<>(columnMap.values()));
        return table;
    }

    /**
     * DDL解析 (字符序列 -> 单词序列)
     *
     * @param ddl 字符序列
     * @return 单词序列
     */
    public static List<DDLWord> parseToWord(String ddl) {

        // 字符序列
        char[] charArr = ddl.toCharArray();
        // 单词序列
        List<DDLWord> words = new ArrayList<>();
        // 上一个单词
        DDLWord lastWord = new DDLWord();
        // 当前单词
        DDLWord curWord = new DDLWord();
        // 字段序号
        int fieldOrder = 0;

        // 遍历所有字符，按DDL语法拆分出各个单词
        for (int idx = 0; idx < charArr.length; idx++) {
            char c = charArr[idx];
            if (curWord.getType() == DDLWordType.INIT) {
                // 初始状态 -> 判断当前单词的类型 (注意：这里会消耗一个字符)
                judgeCurWordType(charArr, idx, lastWord, curWord);
                continue;
            }
            if (curWord.getType() == DDLWordType.TABLE_NAME) {
                // 表名
                if (CREATE_TABLE.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, CREATE_TABLE.length()))) {
                    // "CREATE TABLE"后面出现"`"，开始获取表名
                    idx = CharArrayUtil.indexOf(charArr, idx, '`');
                    continue;
                }
                if (c == '`') {
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord();
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.FIELD_NAME) {
                // 字段名
                if (c == '`') {
                    fieldOrder++;
                    curWord.setFieldOrder(fieldOrder);
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord(fieldOrder);
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.FIELD_TYPE) {
                // 字段类型
                if (!Character.isLetter(c)) {
                    if (!curWord.isEmpty()) {
                        words.add(curWord);
                        lastWord = curWord;
                        curWord = new DDLWord(fieldOrder);
                        if (c == '(') {
                            idx--;
                        }
                    }
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.FIELD_LENGTH) {
                // 字段长度
                if (c == ')') {
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord(fieldOrder);
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.FIELD_NULLABLE) {
                // 字段可空
                if (NULL.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, NULL.length()))) {
                    curWord.append(charArr, idx - 1, NULL.length());
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord(fieldOrder);
                    idx += (NULL.length() - 1);
                } else if (NOT_NULL.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, NOT_NULL.length()))) {
                    curWord.append(charArr, idx - 1, NOT_NULL.length());
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord(fieldOrder);
                    idx += (NOT_NULL.length() - 1);
                }
            } else if (curWord.getType() == DDLWordType.FIELD_DEFAULT) {
                // 字段默认值
                if (DEFAULT.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, DEFAULT.length()))) {
                    idx += (DEFAULT.length() - 1);
                    continue;
                }
                // 几种常见默认值：0，'0'，''，'1970-01-01 00:00:00'，CURRENT_TIMESTAMP
                if (Character.isWhitespace(c)) {
                    if (!curWord.isEmpty()) {
                        // '出现奇数次，说明还没结束，那么当前空白符是默认值的一部分
                        if (CharArrayUtil.countChar(curWord.toCharArray(), '\'') % 2 == 1) {
                            curWord.append(c);
                        } else {
                            words.add(curWord);
                            lastWord = curWord;
                            curWord = new DDLWord(fieldOrder);
                        }
                    }
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.FIELD_COMMENT) {
                // 字段说明
                if (COMMENT.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, COMMENT.length()))) {
                    // "COMMENT"后面出现"'"，开始获取字段说明
                    idx = CharArrayUtil.indexOf(charArr, idx, '\'');
                    continue;
                }
                if (c == '\'') {
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord(fieldOrder);
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.PRIMARY_KEY) {
                // 主键索引
                if (PRIMARY_KEY.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, PRIMARY_KEY.length()))) {
                    // "PRIMARY KEY"后面出现"`"，开始获取主键字段
                    idx = CharArrayUtil.indexOf(charArr, idx, '`');
                    continue;
                }
                if (c == '`') {
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord();
                } else {
                    curWord.append(c);
                }
            } else if (curWord.getType() == DDLWordType.INDEX) {
                // 普通索引 (不处理)
                lastWord = new DDLWord();
                lastWord.setType(DDLWordType.INDEX);
                curWord = new DDLWord();
            } else if (curWord.getType() == DDLWordType.UNIQUE) {
                // 唯一索引 (不处理)
                lastWord = new DDLWord();
                lastWord.setType(DDLWordType.UNIQUE);
                curWord = new DDLWord();
            } else if (curWord.getType() == DDLWordType.TABLE_COMMENT) {
                // 表说明
                if (TABLE_COMMENT.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx - 1, TABLE_COMMENT.length()))) {
                    // "COMMENT="后面出现"'"，开始获取表说明
                    idx = CharArrayUtil.indexOf(charArr, idx, '\'');
                    continue;
                }
                if (c == '\'') {
                    words.add(curWord);
                    lastWord = curWord;
                    curWord = new DDLWord();
                } else {
                    curWord.append(c);
                }
            }
        }

        return words;

    }

    /**
     * 判断当前单词的类型
     *
     * @param charArr  字符序列
     * @param idx      当前读取位置
     * @param lastWord 上一个单词
     * @param curWord  当前单词
     */
    private static void judgeCurWordType(char[] charArr, int idx, DDLWord lastWord, DDLWord curWord) {
        if (CREATE_TABLE.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, CREATE_TABLE.length()))) {
            curWord.setType(DDLWordType.TABLE_NAME);
        } else if (charArr[idx] == '`'
                && (lastWord.getType() == DDLWordType.TABLE_NAME || DDLWordType.isField(lastWord.getType()))) {
            curWord.setType(DDLWordType.FIELD_NAME);
        } else if (charArr[idx] == ' ' && lastWord.getType() == DDLWordType.FIELD_NAME) {
            curWord.setType(DDLWordType.FIELD_TYPE);
        } else if ((charArr[idx] == '(') && lastWord.getType() == DDLWordType.FIELD_TYPE) {
            curWord.setType(DDLWordType.FIELD_LENGTH);
        } else if ((NULL.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, NULL.length()))
                || NOT_NULL.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, NOT_NULL.length())))
                && DDLWordType.isField(lastWord.getType())) {
            curWord.setType(DDLWordType.FIELD_NULLABLE);
        } else if (DEFAULT.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, DEFAULT.length()))
                && DDLWordType.isField(lastWord.getType())) {
            curWord.setType(DDLWordType.FIELD_DEFAULT);
        } else if (COMMENT.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, COMMENT.length()))
                && DDLWordType.isField(lastWord.getType())) {
            curWord.setType(DDLWordType.FIELD_COMMENT);
        } else if (PRIMARY_KEY.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, PRIMARY_KEY.length()))
                && (DDLWordType.isField(lastWord.getType()) || DDLWordType.isIndex(lastWord.getType()))) {
            curWord.setType(DDLWordType.PRIMARY_KEY);
        } else if (INDEX.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, INDEX.length()))
                && (DDLWordType.isField(lastWord.getType()) || DDLWordType.isIndex(lastWord.getType()))) {
            curWord.setType(DDLWordType.INDEX);
        } else if (UNIQUE.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, UNIQUE.length()))
                && (DDLWordType.isField(lastWord.getType()) || DDLWordType.isIndex(lastWord.getType()))) {
            curWord.setType(DDLWordType.UNIQUE);
        } else if (TABLE_COMMENT.equalsIgnoreCase(CharArrayUtil.toString(charArr, idx, TABLE_COMMENT.length()))) {
            curWord.setType(DDLWordType.TABLE_COMMENT);
        } else {
            curWord.setType(DDLWordType.INIT);
        }
    }

}
