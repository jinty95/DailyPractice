package test.cn.jinty.util.math;

import cn.jinty.util.math.BigDecimalUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * BigDecimal - 工具类 - 测试
 *
 * @author Jinty
 * @date 2025/5/10
 */
public class BigDecimalUtilTest {

    @Test
    public void testCalWeightAverage() {
        List<DataObj> list = new ArrayList<>();
        list.add(new DataObj(1, new BigDecimal("9.9"), 5));
        list.add(new DataObj(1, new BigDecimal("29.9"), 3));
        list.add(new DataObj(1, new BigDecimal("99.9"), 1));
        System.out.println("购物清单：" + list);
        BigDecimal res = BigDecimalUtil.calWeightAverage(list, DataObj::getPrice, a -> BigDecimal.valueOf(a.getQty()));
        System.out.println("平均价格：" + res);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class DataObj {
        private long id;
        private BigDecimal price;
        private int qty;
    }

}
