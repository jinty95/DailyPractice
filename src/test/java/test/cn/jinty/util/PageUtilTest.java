package test.cn.jinty.util;

import cn.jinty.util.PageUtil;
import cn.jinty.util.collection.ListUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
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
        final String EVENT = "根据页码及页面大小计算起始索引";
        Assert.assertEquals(EVENT, 0, PageUtil.getPageStart(1, 10));
        Assert.assertEquals(EVENT, 900, PageUtil.getPageStart(10, 100));
        Assert.assertEquals(EVENT, 450, PageUtil.getPageStart(45, 10));
    }

    @Test
    public void testGetPageCount() {
        final String EVENT = "根据总行数及页面大小计算总页数";
        Assert.assertEquals(EVENT, 0, PageUtil.getPageCount(0, 10));
        Assert.assertEquals(EVENT, 100, PageUtil.getPageCount(1000, 10));
        Assert.assertEquals(EVENT, 101, PageUtil.getPageCount(1005, 10));
    }

    @Test
    public void testGetPage() {
        final String EVENT = "根据总行数及页面大小获取列表中的一页数据";
        List<Integer> list = ListUtil.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertTrue(EVENT, ListUtil.equals(ListUtil.asList(1, 2), PageUtil.getPage(list, 1, 2)));
        Assert.assertTrue(EVENT, ListUtil.equals(ListUtil.asList(1, 2, 3), PageUtil.getPage(list, 1, 3)));
        Assert.assertTrue(EVENT, ListUtil.equals(ListUtil.asList(4, 5, 6), PageUtil.getPage(list, 2, 3)));
        Assert.assertTrue(EVENT, ListUtil.equals(ListUtil.asList(7, 8, 9), PageUtil.getPage(list, 3, 3)));
        Assert.assertTrue(EVENT, ListUtil.equals(new ArrayList<>(), PageUtil.getPage(list, 4, 3)));
        Assert.assertTrue(EVENT, ListUtil.equals(ListUtil.asList(1, 2, 3, 4, 5, 6), PageUtil.getPage(list, 1, 6)));
        Assert.assertTrue(EVENT, ListUtil.equals(ListUtil.asList(7, 8, 9), PageUtil.getPage(list, 2, 6)));
    }

    @Test
    public void testGetPageByMock() {
        List<Integer> list = ListUtil.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 正常方法调用
        System.out.println("正常方法调用");
        getPage(list);
        // Mock静态方法
        MockedStatic<PageUtil> pageUtilMock = Mockito.mockStatic(PageUtil.class);
        // 设置输入输出 (未设置的输入会返回空)
        pageUtilMock.when(() -> PageUtil.getPage(list, 1, 2)).thenReturn(ListUtil.asList(1, 2, 3));
        pageUtilMock.when(() -> PageUtil.getPage(list, 2, 2)).thenReturn(ListUtil.asList(4, 5, 6));
        pageUtilMock.when(() -> PageUtil.getPage(list, 9, 9)).thenThrow(new IllegalArgumentException("非法参数"));
        // Mock方法调用
        System.out.println("\nMock方法调用");
        getPage(list);
        // Mock的作用：把很难正常调用走通的方法，通过Mock走通，可以在依赖不可用时，也能正常测试当前的功能
    }

    private void getPage(List<Integer> list) {
        int[][] arr = {{1, 2}, {2, 2}, {3, 3}, {9, 9}};
        for (int[] a : arr) {
            System.out.printf("list=%s, pageNum=%s, pageSize=%s, result=%s\n", list, a[0], a[1], PageUtil.getPage(list, a[0], a[1]));
        }
    }

}
