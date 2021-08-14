package cn.jinty.design.behavior.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {

        List<Person> peoples = new ArrayList<>();
        peoples.add(new Person("Mike",20,"Male","Single"));
        peoples.add(new Person("Lucy",26,"Female","Married"));
        peoples.add(new Person("Rebacca",23,"Female","Single"));

        Criteria criteriaMale = new CriteriaMale();
        Criteria criteriaSingle = new CriteriaSingle();

        System.out.println(peoples);
        System.out.println(criteriaMale.meetCriteria(peoples));
        System.out.println(criteriaSingle.meetCriteria(peoples));

    }
}
