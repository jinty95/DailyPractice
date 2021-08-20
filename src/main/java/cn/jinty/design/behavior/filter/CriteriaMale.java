package cn.jinty.design.behavior.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 标准 - 男性
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class CriteriaMale implements Criteria{

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> list = new ArrayList<>();
        if(persons!=null){
            for (Person person : persons) {
                if("Male".equalsIgnoreCase(person.getGender())){
                    list.add(person);
                }
            }
        }
        return list;
    }

}
