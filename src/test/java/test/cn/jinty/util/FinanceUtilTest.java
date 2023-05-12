package test.cn.jinty.util;

import cn.jinty.util.FinanceUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 财务 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/4/22
 **/
public class FinanceUtilTest {

    @Test
    public void testGetTaxAndGetAmount() {
        BigDecimal amount = BigDecimal.valueOf(8888L);
        BigDecimal taxRate = BigDecimal.valueOf(6L);
        System.out.printf("金额=%s, 税率=%s%n", amount, taxRate);
        BigDecimal tax = FinanceUtil.getTax(amount, taxRate);
        System.out.printf("税额=%s%n", tax);
        BigDecimal amountWithTax = amount.add(tax);
        System.out.printf("含税金额=%s%n", amountWithTax);
        System.out.printf("原始金额=%s%n", FinanceUtil.getAmount(amountWithTax, taxRate));
    }

    @Test
    public void testFormatAmount() {
        Number amount = 0;
        System.out.println(FinanceUtil.formatAmount(amount));
        amount = 8.8F;
        System.out.println(FinanceUtil.formatAmount(amount));
        amount = 8888.888D;
        System.out.println(FinanceUtil.formatAmount(amount));
        amount = BigDecimal.valueOf(800800800800.88888);
        System.out.println(FinanceUtil.formatAmount(amount));
    }

    @Test
    public void testSplitAvg() {
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(10), 2)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(200), 3)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(300), 7)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(300), 11)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(400), 11)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(600), 13)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(750), 19)));
        System.out.println(Arrays.toString(FinanceUtil.splitAvg(BigDecimal.valueOf(800), 19)));
    }

    @Test
    public void testTransToChineseCapitalAmount() {
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("0010000.0100")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("0000000.0000")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("100000000.0000")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("100000015.0000")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("123406789.1200")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("123456789.1200")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("103213.83")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("1994.1115")));
        System.out.println(FinanceUtil.transToChineseCapitalAmount(new BigDecimal("19941115")));
    }

}
