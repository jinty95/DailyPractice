package cn.jinty.design.composite;

import java.util.List;

/**
 * 员工(对象包含对象)
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Employee {

    private String id;
    private String name;
    private List<Employee> subordinates;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subordinates=" + subordinates +
                '}';
    }
}
