package cn.jinty.util.collection;

/**
 * 字符数组 - 工具类
 *
 * @author Jinty
 * @date 2023/3/5
 */
public final class CharArrayUtil {

    private CharArrayUtil() {
    }

    /**
     * 获取字符首次出现的位置
     *
     * @param arr 字符数组
     * @param c   字符
     * @return 首次出现的位置，不存在时返回-1
     */
    public static int indexOf(char[] arr, char c) {
        return indexOf(arr, 0, c);
    }

    /**
     * 获取字符首次出现的位置
     *
     * @param arr   字符数组
     * @param begin 搜索起始位置
     * @param c     字符
     * @return 首次出现的位置，不存在时返回-1
     */
    public static int indexOf(char[] arr, int begin, char c) {
        for (int i = begin; i < arr.length; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 转为字符串
     *
     * @param arr   字符数组
     * @param begin 起始位置
     * @param count 字符数量
     * @return 字符串
     */
    public static String toString(char[] arr, int begin, int count) {
        if (begin + count > arr.length) {
            count = arr.length - begin;
        }
        return new String(arr, begin, count);
    }

    /**
     * 在字符数组中统计某个字符出现的次数
     *
     * @param arr 字符数组
     * @param c   字符
     * @return 字符出现次数
     */
    public static int countChar(char[] arr, char c) {
        int count = 0;
        for (char a : arr) {
            if (a == c) {
                count++;
            }
        }
        return count;
    }

}
