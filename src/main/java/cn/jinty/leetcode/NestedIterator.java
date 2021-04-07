package cn.jinty.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 嵌套列表迭代器
 *
 * @author jinty
 * @date 2021/3/23
 **/
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> list;
    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        nestedList2List(nestedList,list);
        iterator = list.listIterator();
    }

    private void nestedList2List(List<NestedInteger> nestedList,List<Integer> list){
        for(NestedInteger nestedInteger : nestedList){
            if(nestedInteger.isInteger()){
                list.add(nestedInteger.getInteger());
            }else{
                nestedList2List(nestedInteger.getList(),list);
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

}

