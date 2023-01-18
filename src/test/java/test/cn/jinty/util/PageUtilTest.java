package test.cn.jinty.util;

import cn.jinty.util.collection.ListUtil;
import cn.jinty.util.PageUtil;
import org.junit.Test;

import java.util.List;

/**
 * 分页 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2022/1/1
 **/
public class PageUtilTest {

    @Test
    public void testGetPageStart() {
        System.out.println(PageUtil.getPageStart(1, 10));
        System.out.println(PageUtil.getPageStart(10, 100));
        System.out.println(PageUtil.getPageStart(45, 10));
    }

    @Test
    public void testGetPageCount() {
        System.out.println(PageUtil.getPageCount(0, 10));
        System.out.println(PageUtil.getPageCount(1000, 10));
        System.out.println(PageUtil.getPageCount(1005, 10));
    }

    @Test
    public void testGetPage() {
        List<Integer> list = ListUtil.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(PageUtil.getPage(list, 1, 2));
        System.out.println(PageUtil.getPage(list, 1, 3));
        System.out.println(PageUtil.getPage(list, 2, 3));
        System.out.println(PageUtil.getPage(list, 3, 3));
        System.out.println(PageUtil.getPage(list, 4, 3));
        System.out.println(PageUtil.getPage(list, 1, 6));
        System.out.println(PageUtil.getPage(list, 2, 6));
    }

}
