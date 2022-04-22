package cn.jinty.entity.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页 - 请求体
 *
 * @author Jinty
 * @date 2022/4/22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    // 页码
    private Integer pageNum;

    // 大小
    private Integer pageSize;

    /**
     * 默认分页
     *
     * @return 分页请求体
     */
    public static PageRequest defaults() {
        return new PageRequest(1, 10);
    }

    /**
     * 查询一行数据
     *
     * @return 分页请求体
     */
    public static PageRequest one() {
        return new PageRequest(1, 1);
    }

    /**
     * 查询所有数据
     *
     * @return 分页请求体
     */
    public static PageRequest all() {
        return new PageRequest(1, Integer.MAX_VALUE);
    }

}
