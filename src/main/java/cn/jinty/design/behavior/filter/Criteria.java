package cn.jinty.design.behavior.filter;

import java.util.List;

/**
 * 标准
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public interface Criteria {

    /**
     * 过滤出符合标准的对象列表
     *
     * @param persons 输入列表
     * @return 输出列表
     */
    List<Person> meetCriteria(List<Person> persons);

}
