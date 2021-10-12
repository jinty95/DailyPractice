package cn.jinty.design.behavior.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 标准 - 单身
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> list = new ArrayList<>();
        if (persons != null) {
            for (Person person : persons) {
                if ("Single".equalsIgnoreCase(person.getMaritalStatus())) {
                    list.add(person);
                }
            }
        }
        return list;
    }

}
