package cn.jinty.sensitive;

import java.util.HashMap;

/**
 * @Description 敏感词树
 * @Author jinty
 * @Date 2019年9月16日10点51分
 */
public class SensitiveWordTree {
    //从头结点起到上一个点所代表的词是否敏感
    private boolean isSensitive;
    //从当前结点往下链接构建新词
    private HashMap<Character,SensitiveWordTree> map;

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public void setMap(HashMap<Character, SensitiveWordTree> map) {
        this.map = map;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public HashMap<Character, SensitiveWordTree> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "SensitiveWordTree{" +
                "isSensitive=" + isSensitive +
                ", map=" + map +
                '}';
    }
}
