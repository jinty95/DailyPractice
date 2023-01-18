package test.cn.jinty.util.security;

import cn.jinty.util.StringUtil;
import cn.jinty.util.security.RsaEncryptUtil;
import org.junit.Test;

import java.security.KeyPair;

/**
 * RSA非对称加密 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public class RsaEncryptUtilTest {

    @Test
    public void testGenKeyPair() {
        try {
            KeyPair keyPair = RsaEncryptUtil.genKeyPair();
            System.out.println("公钥(base64)：" + RsaEncryptUtil.getPublicKey(keyPair));
            System.out.println("私钥(base64)：" + RsaEncryptUtil.getPrivateKey(keyPair));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRsa() {
        // Data must not be longer than 245 bytes
        String content = StringUtil.repeat("A", 245);
        try {
            KeyPair keyPair = RsaEncryptUtil.genKeyPair();
            String pubStr = RsaEncryptUtil.getPublicKey(keyPair);
            String priStr = RsaEncryptUtil.getPrivateKey(keyPair);
            String encryptContent = RsaEncryptUtil.publicEncrypt(content, pubStr);
            String decryptContent = RsaEncryptUtil.privateDecrypt(encryptContent, priStr);
            System.out.println("公钥(base64)：" + pubStr);
            System.out.println("私钥(base64)：" + priStr);
            System.out.println("原文：" + content);
            System.out.println("密文(base64)：" + encryptContent);
            System.out.println("解密原文：" + decryptContent);
            System.out.println("解密成功：" + content.equals(decryptContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
