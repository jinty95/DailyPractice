package cn.jinty.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 财务 - 工具类
 *
 * @author Jinty
 * @date 2022/4/22
 **/
public final class FinanceUtil {

    private FinanceUtil() {
    }

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100L);
    private static final int SCALE = 2;

    /**
     * 计算税额
     *
     * @param amount  金额
     * @param taxRate 税率(百分数，输入6，表示6%)
     * @return 税额
     */
    public static BigDecimal getTax(BigDecimal amount, BigDecimal taxRate) {
        if (amount == null) {
            return null;
        }
        if (taxRate == null) {
            return BigDecimal.ZERO;
        }
        return amount.multiply(taxRate).divide(HUNDRED, SCALE, RoundingMode.HALF_UP);
    }

    /**
     * 计算不含税金额
     *
     * @param amountWithTax 含税金额
     * @param taxRate       税率(百分数，输入6，表示6%)
     * @return 不含税金额
     */
    public static BigDecimal getAmount(BigDecimal amountWithTax, BigDecimal taxRate) {
        if (amountWithTax == null) {
            return null;
        }
        if (taxRate == null) {
            return amountWithTax;
        }
        return amountWithTax.multiply(HUNDRED).divide(taxRate.add(HUNDRED), SCALE, RoundingMode.HALF_UP);
    }

    /**
     * 格式化金额
     *
     * @param amount 金额
     * @return 格式化金额(千分位显示)
     */
    public static String formatAmount(Number amount) {
        if (amount == null) {
            return StringUtil.EMPTY;
        }
        // ','右侧表示多少位整数用一个','分割，'.'右侧表示小数最多精确到多少位
        NumberFormat nf = new DecimalFormat("#,##0.00");
        return nf.format(amount);
    }

    /**
     * 金额平均分摊
     *
     * @param amount 金额
     * @param n      个数
     * @return 结果
     */
    public static BigDecimal[] splitAvg(BigDecimal amount, int n) {
        // 无法分摊
        if (amount == null || n < 1) {
            return new BigDecimal[0];
        }
        // 倒挤算法：平均分摊，无法均分时，在最后一个部分补上尾差
        BigDecimal avg = amount.divide(BigDecimal.valueOf(n), SCALE, RoundingMode.HALF_UP);
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal[] result = new BigDecimal[n];
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                sum = sum.add(avg);
                result[i] = avg;
            } else {
                result[i] = amount.subtract(sum);
            }
        }
        return result;
    }

    // 中文大写数字
    private static final String[] CHINESE_CAPITAL_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    // 中文大写整数进位 (最大万亿)
    private static final String[] CHINESE_CAPITAL_INTEGER_CARRY = {"万", "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", ""};
    // 中文大写小数进位 (最小分)
    private static final String[] CHINESE_CAPITAL_DECIMAL_CARRY = {"角", "分"};

    /**
     * 将金额转成中文大写金额
     *
     * @param amount 金额
     * @return 中文大写金额
     */
    public static String transToChineseCapitalAmount(BigDecimal amount) {
        if (amount == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        // 保留两位小数即可
        String str = amount.setScale(SCALE, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        // 拆成整数部分与小数部分
        String[] arr = str.split("\\.");
        String integer = arr[0];
        String decimal = arr.length > 1 ? arr[1] : null;
        // 可表示的上限
        if (integer.length() > 13) {
            throw new IllegalArgumentException("金额必须小于10^14：amount=" + amount.stripTrailingZeros().toPlainString());
        }
        // 转换整数部分
        for (int i = 0; i < integer.length(); i++) {
            char c = integer.charAt(i);
            int carry = CHINESE_CAPITAL_INTEGER_CARRY.length - integer.length() + i;
            if (c == '0') {
                // 特殊数位处理：亿、万
                if (carry == 4) {
                    result.append(CHINESE_CAPITAL_INTEGER_CARRY[4]);
                } else if (carry == 8 && !CHINESE_CAPITAL_INTEGER_CARRY[4].equals(String.valueOf(result.charAt(result.length() - 1)))) {
                    result.append(CHINESE_CAPITAL_INTEGER_CARRY[8]);
                }
            } else {
                // 数字与数字中间的任意多个0用一个零表示
                if (i > 0 && integer.charAt(i - 1) == '0') {
                    result.append(CHINESE_CAPITAL_NUMBER[0]);
                }
                // 数字
                result.append(CHINESE_CAPITAL_NUMBER[c - '0']);
                // 数位
                result.append(CHINESE_CAPITAL_INTEGER_CARRY[carry]);
            }
        }
        if (result.length() == 0) {
            result.append(CHINESE_CAPITAL_NUMBER[0]);
        }
        result.append("元");
        // 转换小数部分
        if (decimal != null) {
            for (int i = 0; i < decimal.length(); i++) {
                char c = decimal.charAt(i);
                int carry = CHINESE_CAPITAL_DECIMAL_CARRY.length - decimal.length() + i;
                if (c == '0') {
                    // 0角用一个零表示
                    if (i == 0) {
                        result.append(CHINESE_CAPITAL_NUMBER[0]);
                    }
                    continue;
                }
                // 数字
                result.append(CHINESE_CAPITAL_NUMBER[c - '0']);
                // 数位
                result.append(CHINESE_CAPITAL_DECIMAL_CARRY[carry]);
            }
        } else {
            result.append("整");
        }
        return result.toString();
    }

}
