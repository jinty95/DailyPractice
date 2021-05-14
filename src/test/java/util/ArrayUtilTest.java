package util;

import cn.jinty.util.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 数组工具类 - 测试
 *
 * @author jinty
 * @date 2021/4/22
 **/
public class ArrayUtilTest {

    @Test
    public void testGenerateArray(){
        int[] arr = ArrayUtil.generateArray(10);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testInputArray(){
        int[] arr = ArrayUtil.inputArray();
        System.out.println(Arrays.toString(arr));
    }

}
