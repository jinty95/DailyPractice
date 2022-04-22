package cn.jinty.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

}
