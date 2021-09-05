package test.cn.jinty.design.struct.composite;

import cn.jinty.design.struct.composite.Employee;

import java.util.ArrayList;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        Employee ceo = new Employee("0","Boss");
        ceo.setSubordinates(new ArrayList<>());
        ceo.getSubordinates().add(new Employee("1","Empoyee1"));
        ceo.getSubordinates().add(new Employee("2","Empoyee2"));
        System.out.println(ceo);
    }
}
