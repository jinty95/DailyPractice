package cn.jinty.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表工具
 *
 * @author jinty
 * @date 2020/12/8
 **/
public final class ListUtil {

    /**
     * 列表按数量分组
     *
     * @param list 原始列表
     * @param num 每组个数
     * @param <T> 泛型
     * @return 分组结果
     */
    public static <T> List<List<T>> splitByNum(List<T> list,int num){
        List<List<T>> splitList = new ArrayList<>();
        if(list.isEmpty()){
            return splitList;
        }
        int size = list.size();
        int p = 0;
        while(p < size){
            List<T> oneList = new ArrayList<>();;
            for(int i=0; i<num && p<size; i++){
                oneList.add(list.get(p++));
            }
            splitList.add(oneList);
        }
        return splitList;
    }

}
