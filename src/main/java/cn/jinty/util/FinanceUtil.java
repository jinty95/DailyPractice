package cn.jinty.util;

import cn.jinty.util.StringUtil;

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

}
