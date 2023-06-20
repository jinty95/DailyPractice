package cn.jinty.util.string;

/**
 * 转义字符串 - 工具类
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class EscapeStringUtil {

    private EscapeStringUtil() {
    }

    /**
     * 添加转义字符 (符号为 \，英文名为 Escape Character)
     * 使得具有特殊含义的字符，呈现其字面上的文本
     * 例如：
     * "\n"表示一个换行符，处理后变成"\\n"，表示文本\n
     * "\\"表示一个反斜杠，处理后变成"\\\\"，表示文本\\
     * "\""表示一个双引号，处理后变成"\\\""，表示文本\"
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String escape(String s) {
        if (StringUtil.isEmpty(s)) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 移除转义字符 (符号为 \，英文名为 Escape Character)
     * 使得一个字面上的文本，呈现其对应的特殊含义
     * 例如：
     * "\\n"表示文本\n，处理后变成"\n"，表示一个换行符
     * "\\\\"表示文本\\，处理后变成"\\"，表示一个反斜杠
     * "\\\""表示文本\"，处理后变成"\""，表示一个双引号
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String unescape(String s) {
        if (StringUtil.isEmpty(s)) {
            return StringUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        boolean hasBackSlash = false;
        for (char c : s.toCharArray()) {
            if (hasBackSlash) {
                hasBackSlash = false;
                switch (c) {
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    default:
                        sb.append('\\');
                        sb.append(c);
                }
                continue;
            } else if (c == '\\') {
                hasBackSlash = true;
                continue;
            }
            sb.append(c);
        }
        if (hasBackSlash) {
            sb.append('\\');
        }
        return sb.toString();
    }

    /**
     * 递归移除转义字符，直到无法继续移除为止
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String unescapeAll(String s) {
        if (StringUtil.isEmpty(s)) {
            return StringUtil.EMPTY;
        }
        String unescape = unescape(s);
        if (unescape.equals(s)) {
            return unescape;
        }
        return unescapeAll(unescape);
    }

}
