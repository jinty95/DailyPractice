package test.cn.jinty.util.encrypt;

import cn.jinty.util.encrypt.AesEncryptUtil;
import org.junit.Test;

import javax.crypto.SecretKey;

/**
 * AES对称加密 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public class AesEncryptUtilTest {

    @Test
    public void testGenAesKey() {
        try {
            SecretKey secretKey = AesEncryptUtil.genAesKey();
            System.out.println("AES密钥(base64)：" + AesEncryptUtil.getAesKey(secretKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAes() {
        String[] str = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ"};
        try {
            String keyStr = AesEncryptUtil.getAesKey(AesEncryptUtil.genAesKey());
            System.out.println("AES密钥(base64)：" + keyStr);
            System.out.println();
            for (int i = 0; i <= 9; i++) {
                // 首次由于初始化加密器，执行时间比较长
                long begin = System.currentTimeMillis();
                String encrypt = AesEncryptUtil.aesEncrypt(str[i], keyStr);
                String decrypt = AesEncryptUtil.aesDecrypt(encrypt, keyStr);
                long end = System.currentTimeMillis();
                System.out.println("原文：" + str[i]);
                System.out.println("密文(base64)：" + encrypt);
                System.out.println("解密原文：" + decrypt);
                System.out.println("解密成功：" + str[i].equals(decrypt));
                System.out.println("耗时: " + (end - begin));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
