package cn.jinty.util;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.util.Arrays;

/**
 * 加密工具类测试
 *
 * @author jinty
 * @date 2021/4/9
 **/
public class EncryptUtilTest {

    @Test
    public void testGenerateAesKey(){

        BASE64Encoder base64Encoder = new BASE64Encoder();

        //生成AES密钥
        byte[] bytes = EncryptUtil.generateAesKey();
        //转为字符串显示
        String aesKeyHex = StringUtil.byteToHexString(bytes);
        String aeskeyBase64 = base64Encoder.encode(bytes);
        System.out.println("AesKey[hex] : " + aesKeyHex);
        System.out.println("AesKey[base64] : " + aeskeyBase64);

        //进行SHA1处理并取前16位
        byte[] newBytes = Arrays.copyOf(EncryptUtil.sha1(aesKeyHex),16);
        //转为字符串显示
        System.out.println("NewAesKey[hex] : "+StringUtil.byteToHexString(newBytes));
        System.out.println("NewAesKey[base64] : " + base64Encoder.encode(newBytes));

    }

}
