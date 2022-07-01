package test.cn.jinty.util;

import cn.jinty.util.DateUtil;
import cn.jinty.util.IdUtil;
import cn.jinty.util.RetryUtil;
import org.junit.Test;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 重试 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/6/23
 **/
public class RetryUtilTest {

    @Test
    public void test1() {
        String desc = "根据时间判断星座";
        Date param = new Date();
        String result;
        result = RetryUtil.retryForFunction(DateUtil::getConstellation, param, desc, 3);
        System.out.println(desc + "：param=" + DateUtil.format(param) + "，result=" + result);
        param = null;
        result = RetryUtil.retryForFunction(DateUtil::getConstellation, param, desc, 3);
        System.out.println(desc + "：param=" + DateUtil.format(param) + "，result=" + result);
    }

    @Test
    public void test2() {
        String desc = "断言对象非空";
        Object param = new Object();
        Consumer<Object> func = o -> {
            if (o == null) {
                throw new RuntimeException("对象为空");
            }
        };
        RetryUtil.retryForConsumer(func, param, desc, 3);
        param = null;
        RetryUtil.retryForConsumer(func, param, desc, 3);
    }

    @Test
    public void test3() {
        String desc = "生成ID";
        String result = "";
        Supplier<String> func = () -> IdUtil.number("A", 1);
        for (int i = 0; i < 10; i++) {
            result = RetryUtil.retryForSupplier(func, desc, 3);
            System.out.println(desc + "：result=" + result);
        }
    }

}
