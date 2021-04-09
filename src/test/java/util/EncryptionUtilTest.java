package util;

import cn.jinty.utils.EncryptionUtil;
import cn.jinty.utils.StringUtil;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.xml.bind.DatatypeConverter;

/**
 * 加密工具类测试
 *
 * @author jinty
 * @date 2021/4/9
 **/
public class EncryptionUtilTest {

    @Test
    public void testGenerateAesKey(){
        //生成AES密钥
        byte[] bytes = EncryptionUtil.generateAesKey();
        //转为字符串显示
        System.out.println(DatatypeConverter.printHexBinary(bytes));
        System.out.println(StringUtil.byteToHexString(bytes));
        System.out.println(new BASE64Encoder().encode(bytes));
    }

}
