package test.cn.jinty.util;

import cn.jinty.util.EncryptUtil;
import cn.jinty.util.StringUtil;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.util.Arrays;
import java.util.Objects;

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
        byte[] newBytes = Arrays.copyOf(Objects.requireNonNull(EncryptUtil.sha1(aesKeyHex)),16);
        //转为字符串显示
        System.out.println("NewAesKey[hex] : "+StringUtil.byteToHexString(newBytes));
        System.out.println("NewAesKey[base64] : " + base64Encoder.encode(newBytes));

    }

    @Test
    public void testAes(){
        String[] str = {"AAA","BBB","CCC","DDD","EEE","FFF","GGG","HHH","III","JJJ"};
        //key不能任意指定，只能为128b、192b、256b
        String key = "0123456789ABCDEF";
        try{
            for(int i=0;i<=9;i++){
                //首次由于初始化加密器，执行时间比较长
                long begin = System.currentTimeMillis();
                String encrypt = EncryptUtil.aesEncrypt(str[i],key);
                System.out.println(encrypt);
                str[i] = EncryptUtil.aesDecrypt(encrypt,key);
                System.out.println(str[i]);
                long end = System.currentTimeMillis();
                System.out.println("used time : "+(end-begin));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testXor(){
        String plainText = "Hello";
        int key = 2021;
        String cypherText = EncryptUtil.xorEncrypt(plainText,key);
        plainText = EncryptUtil.xorDecrypt(cypherText,key);
        System.out.println(cypherText);
        System.out.println(plainText);
    }

    @Test
    public void testMD5(){
        String str = "Hello";
        System.out.println(EncryptUtil.md5Str(str));
    }

    @Test
    public void testSha1(){
        String str = "Hello";
        System.out.println(EncryptUtil.sha1Str(str));
    }

}
