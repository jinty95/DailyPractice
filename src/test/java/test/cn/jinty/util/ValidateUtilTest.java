package test.cn.jinty.util;

import cn.jinty.util.ValidateUtil;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 校验 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/12/8
 **/
public class ValidateUtilTest {

    @Test
    public void testNotNull() {
        try {
            ValidateUtil.notNull(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ValidateUtil.notNull(null, "请求体不能为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotBlank() {
        try {
            ValidateUtil.notBlank(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ValidateUtil.notBlank(" ", "某字段不能为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotEmpty() {
        try {
            ValidateUtil.notEmpty(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ValidateUtil.notEmpty(new ArrayList<>(), "某字段不能为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotFalse() {
        int limit = 500;
        for (int i = 100; i <= 1000; i += 100) {
            try {
                ValidateUtil.notFalse(i <= limit, "输入" + i + "超过上限" + limit);
                System.out.println("校验通过：i=" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
