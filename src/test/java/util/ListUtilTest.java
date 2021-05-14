package util;

import cn.jinty.util.ListUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 列表工具类 - 测试
 *
 * @author jinty
 * @date 2021/5/14
 */
public class ListUtilTest {

    @Test
    public void testSplitByNum(){
        List<Integer> list = Arrays.asList(
                1,2,3,4,5,6,7,8,9,10
        );
        System.out.println(ListUtil.splitByNum(list,3));
        System.out.println(ListUtil.splitByNum(list,4));
    }

}
