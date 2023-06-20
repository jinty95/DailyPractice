package cn.jinty.util.string;

import java.util.Random;

/**
 * 随机字符串 - 工具类
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public final class RandomStringUtil {

    private RandomStringUtil() {
    }

    // 数字
    private static final char[] DIGIT = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    // 字母
    private static final char[] LETTER = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    // 随机数
    private static final Random RANDOM = new Random();

    /**
     * 生成随机字符串(数字)
     *
     * @param length 长度
     * @return 字符串
     */
    public static String randomDigit(int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append(DIGIT[RANDOM.nextInt(DIGIT.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串(字母)
     *
     * @param length 长度
     * @return 字符串
     */
    public static String randomLetter(int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append(LETTER[RANDOM.nextInt(LETTER.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串(数字+字母)
     *
     * @param length 长度
     * @return 字符串
     */
    public static String random(int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            int rand = RANDOM.nextInt(36);
            if (rand < 10) {
                sb.append(DIGIT[rand]);
            } else {
                sb.append(LETTER[rand - 10]);
            }
        }
        return sb.toString();
    }

}
