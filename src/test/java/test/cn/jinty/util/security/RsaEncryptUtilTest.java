package test.cn.jinty.util.security;

import cn.jinty.util.StringUtil;
import cn.jinty.util.security.MessageDigestUtil;
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

    // 利用RSA实现签名与验签
    @Test
    public void testSignature() {
        try {
            // 生成RSA密钥对
            KeyPair keyPair = RsaEncryptUtil.genKeyPair();
            String pubStr = RsaEncryptUtil.getPublicKey(keyPair);
            String priStr = RsaEncryptUtil.getPrivateKey(keyPair);
            System.out.println("公钥(base64)：" + pubStr);
            System.out.println("私钥(base64)：" + priStr);
            // 将报文计算出MD5摘要，再将其用私钥加密，得到签名
            String user = "admin";
            long timestamp = System.currentTimeMillis();
            String userAndTimestamp = String.format("user=%s&timestamp=%s", user, timestamp);
            String md5 = MessageDigestUtil.md5Str(userAndTimestamp);
            String signature = RsaEncryptUtil.privateEncrypt(md5, priStr);
            // 客户端用"报文+签名"构建url
            String url = "http://xxx.com?" + userAndTimestamp + "&signature=" + signature;
            System.out.println("MD5：" + md5);
            System.out.println("访问路径：" + url);
            // 服务器验签
            String parseMd5 = RsaEncryptUtil.publicDecrypt(signature, pubStr);
            String calculateMd5 = MessageDigestUtil.md5Str(userAndTimestamp);
            if (parseMd5.equals(calculateMd5)) {
                System.out.println("验签成功");
            } else {
                System.out.println("验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
