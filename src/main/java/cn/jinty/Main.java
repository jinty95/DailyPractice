package cn.jinty;

import cn.jinty.utils.EncryptUtil;
import sun.misc.BASE64Encoder;

import java.util.Arrays;

/**
 * 主函数
 *
 * @author jinty
 * @date 2020/11/24
 **/
public class Main {

    public static void main(String[] args){

        byte[] bytes = Arrays.copyOf(EncryptUtil.sha1("X3ewzLzYBKzM9vwn"),16);
        System.out.println(new BASE64Encoder().encode(bytes));
        System.out.println(new String(bytes));

    }

}
