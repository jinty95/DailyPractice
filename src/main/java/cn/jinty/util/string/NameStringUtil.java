package cn.jinty.util.string;

/**
 * 命名字符串 - 工具类
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class NameStringUtil {

    private NameStringUtil() {
    }

    // 驼峰命名只有一种大小搭配的形式，而下划线命名有全大写和全小写两种形式

    /**
     * 驼峰命名 -> 下划线命名，然后转为全大写形式
     *
     * @param camel 驼峰字符串
     * @return 下划线字符串（全大写形式）
     */
    public static String camelToSnakeThenUpper(String camel) {
        return camelToSnake(camel).toUpperCase();
    }

    /**
     * 驼峰命名 -> 下划线命名
     *
     * @param camel 驼峰字符串
     * @return 下划线字符串（全小写形式）
     */
    public static String camelToSnake(String camel) {
        if (StringUtil.isEmpty(camel)) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camel.length(); i++) {
            char c = camel.charAt(i);
            // 寻找大写字母，在前面添加下划线，并将其转为小写
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append("_");
                }
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 下划线命名 -> 驼峰命名
     *
     * @param snake   下划线字符串
     * @param upFirst 是否首字符大写
     * @return 驼峰字符串
     */
    public static String snakeToCamel(String snake, boolean upFirst) {
        if (StringUtil.isEmpty(snake)) {
            return StringUtil.EMPTY;
        }
        // 先全部转小写
        snake = snake.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < snake.length(); i++) {
            char c = snake.charAt(i);
            // 寻找下划线，将其移除，并将其后一个字母变为大写
            if (c == '_') {
                upFirst = true;
            } else {
                if (upFirst) {
                    c = Character.toUpperCase(c);
                    upFirst = false;
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
