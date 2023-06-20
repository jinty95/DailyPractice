package test.cn.jinty.util.security;

import cn.jinty.util.string.StringUtil;
import cn.jinty.util.security.CustomEncryptUtil;
import org.junit.Test;

import java.util.Base64;
import java.util.Scanner;

/**
 * 自定义加密 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/1/12
 **/
public class CustomEncryptUtilTest {

    @Test
    public void testXor() {
        String content = StringUtil.repeat("Hello", 100);
        int key = 10086;
        String encryptContent = CustomEncryptUtil.xorEncrypt(content, key);
        String decryptContent = CustomEncryptUtil.xorDecrypt(encryptContent, key);
        System.out.println("原文：" + content);
        System.out.println("密文(base64)：" + encryptContent);
        System.out.println("密文：" + new String(Base64.getDecoder().decode(encryptContent)));
        System.out.println("解密原文：" + decryptContent);
        System.out.println("解密成功：" + content.equals(decryptContent));
    }

    @Test
    public void testForeachInt() {
        long begin = System.currentTimeMillis();
        int i = Integer.MIN_VALUE;
        while (i != Integer.MAX_VALUE) {
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("遍历int所有值，耗时=" + (end -begin) + "ms");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数，作为密钥");
        int key = scanner.nextInt();
        System.out.println("您的密钥：" + key);
        System.out.println("请输入一行待加密的文字");
        String content = scanner.next();
        System.out.println("您的原文：" + content);
        String encryptContent = CustomEncryptUtil.xorEncrypt(content, key);
        System.out.println("您的密文：" + encryptContent);
    }

}
