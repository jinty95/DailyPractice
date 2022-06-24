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

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100L);

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
        return amount.multiply(taxRate).divide(HUNDRED, 7, RoundingMode.HALF_UP);
    }

    /**
     * 计算不含税金额
     *
     * @param amountWithTax 含税金额
     * @param taxRate       税率
     * @return 不含税金额
     */
    public static BigDecimal getAmount(BigDecimal amountWithTax, BigDecimal taxRate) {
        if (amountWithTax == null) {
            return null;
        }
        if (taxRate == null) {
            return amountWithTax;
        }
        return amountWithTax.multiply(HUNDRED).divide(taxRate.add(HUNDRED), 7, RoundingMode.HALF_UP);
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

}
