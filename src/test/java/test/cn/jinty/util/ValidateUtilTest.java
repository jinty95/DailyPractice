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

}
