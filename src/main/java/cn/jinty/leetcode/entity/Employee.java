package cn.jinty.leetcode.entity;

import java.util.List;

/**
 * 员工
 *
 * @author Jinty
 * @date 2021/5/4
 */
public class Employee {

    //ID
    public int id;

    //重要性
    public int importance;

    //下属
    public List<Integer> subordinates;

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }

}
