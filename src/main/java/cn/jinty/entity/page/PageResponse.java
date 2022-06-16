package cn.jinty.entity.page;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页 - 响应体
 *
 * @author Jinty
 * @date 2022/4/22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends PageRequest {

    // 总数
    private Integer total;

    // 数据列表
    private List<T> list;

    /**
     * 构造器
     *
     * @param pageRequest 分页请求体
     * @param total       总数
     * @param list        数据列表
     */
    public PageResponse(PageRequest pageRequest, Integer total, List<T> list) {
        super(pageRequest.getPageNum(), pageRequest.getPageSize());
        this.total = total;
        this.list = list;
    }

    /**
     * 空结果
     *
     * @param <T> 泛型
     * @return 分页响应体
     */
    public static <T> PageResponse<T> empty() {
        return new PageResponse<>(0, new ArrayList<>());
    }

    /**
     * 空结果
     *
     * @param pageRequest 分页请求体
     * @param <T>         泛型
     * @return 分页响应体
     */
    public static <T> PageResponse<T> empty(PageRequest pageRequest) {
        return new PageResponse<>(pageRequest, 0, new ArrayList<>());
    }

}
