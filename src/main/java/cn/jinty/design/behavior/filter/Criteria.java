package cn.jinty.design.behavior.filter;

import java.util.List;

/**
 * 标准
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public interface Criteria {

    List<Person> meetCriteria(List<Person> persons);

}
