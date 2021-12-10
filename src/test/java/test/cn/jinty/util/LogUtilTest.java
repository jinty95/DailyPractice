package test.cn.jinty.util;

import cn.jinty.util.LogUtil;
import org.junit.Test;

import static cn.jinty.constant.EntityEnum.*;
import static cn.jinty.constant.OperationEnum.*;
import static cn.jinty.constant.ResultEnum.*;

/**
 * 日志 - 工具类
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public class LogUtilTest {

    @Test
    public void testStandardFormat() {
        System.out.println(LogUtil.standardFormat(USER, INSERT, SUCCESS));
        System.out.println(LogUtil.standardFormat(USER, SELECT, SUCCESS));
        System.out.println(LogUtil.standardFormat(ROLE, UPDATE, FAIL));
        System.out.println(LogUtil.standardFormat(PERMISSION, DELETE, SUCCESS));
    }

    @Test
    public void testSimpleFormat() {
        System.out.println(LogUtil.simpleFormat(USER, INSERT, SUCCESS));
        System.out.println(LogUtil.simpleFormat(USER, SELECT, SUCCESS));
        System.out.println(LogUtil.simpleFormat(ROLE, UPDATE, FAIL));
        System.out.println(LogUtil.simpleFormat(PERMISSION, DELETE, SUCCESS));
    }
    
}
