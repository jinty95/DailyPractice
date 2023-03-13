package cn.jinty.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页 - 工具类
 *
 * @author Jinty
 * @date 2022/1/1
 **/
public final class PageUtil {

    private PageUtil() {
    }

    /**
     * 计算起始索引
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 起始索引
     */
    public static int getPageStart(int pageNum, int pageSize) {
        if (pageNum < 1) {
            throw new IllegalArgumentException("页码不能小于1");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("页面大小不能小于1");
        }
        return (pageNum - 1) * pageSize;
    }

    /**
     * 计算总页数
     *
     * @param total    总行数
     * @param pageSize 页面大小
     * @return 总页数
     */
    public static int getPageCount(int total, int pageSize) {
        if (total < 0) {
            throw new IllegalArgumentException("总记录数不能小于0");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("页面大小不能小于1");
        }
        return total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    /**
     * 获取列表中的一页数据
     *
     * @param list     列表
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param <T>      类型
     * @return 一页数据
     */
    public static <T> List<T> getPage(List<T> list, int pageNum, int pageSize) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        //起始索引
        int pageStart = getPageStart(pageNum, pageSize);
        if (pageStart >= list.size()) {
            return new ArrayList<>();
        }
        //终止索引
        int pageEnd = pageStart + pageSize;
        if (pageEnd > list.size()) {
            pageEnd = list.size();
        }
        return list.subList(pageStart, pageEnd);
    }

}
