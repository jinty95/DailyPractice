package test.cn.jinty.util.security;

import cn.jinty.util.security.DataMaskUtil;
import org.junit.Test;

/**
 * 数据脱敏 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/5/6
 **/
public class DataMaskUtilTest {

    @Test
    public void testMaskPhone() {
        System.out.println(DataMaskUtil.maskPhone(null));
        System.out.println(DataMaskUtil.maskPhone(""));
        System.out.println(DataMaskUtil.maskPhone("1234567"));
        System.out.println(DataMaskUtil.maskPhone("123456789"));
        System.out.println(DataMaskUtil.maskPhone("12345678910"));
    }

    @Test
    public void testMaskName() {
        System.out.println(DataMaskUtil.maskName(null));
        System.out.println(DataMaskUtil.maskName("秦"));
        System.out.println(DataMaskUtil.maskName("张三"));
        System.out.println(DataMaskUtil.maskName("王小二"));
        System.out.println(DataMaskUtil.maskName("王者荣耀"));
        System.out.println(DataMaskUtil.maskName("夏侯李小明"));
    }

    @Test
    public void testMaskIdCard() {
        System.out.println(DataMaskUtil.maskIdCard(null));
        System.out.println(DataMaskUtil.maskIdCard("4452240000"));
        System.out.println(DataMaskUtil.maskIdCard("445224199001010000"));
        System.out.println(DataMaskUtil.maskIdCard("445224199912298888"));
    }

    @Test
    public void testMaskMail() {
        System.out.println(DataMaskUtil.maskMail(null));
        System.out.println(DataMaskUtil.maskMail("123"));
        System.out.println(DataMaskUtil.maskMail("123@qq.com"));
        System.out.println(DataMaskUtil.maskMail("123456789@163.cn"));
    }

}
