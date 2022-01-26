package test.cn.jinty.entity;

import cn.jinty.entity.tuple.Pair;
import cn.jinty.entity.tuple.Triple;
import cn.jinty.util.DateUtil;
import cn.jinty.util.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 元组 - 测试
 *
 * @author jintai.wang
 * @date 2022/1/26
 **/
public class TupleTest {

    @Test
    public void testPair() {
        Pair<Integer, String> pair = new Pair<>(1, "abc");
        System.out.println(pair);
    }

    @Test
    public void testTriple() {
        List<Triple<Integer, String, Date>> triples = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            triples.add(new Triple<>(i, StringUtil.repeat(String.valueOf(i), i), DateUtil.buildDate(2022, i, 1)));
        }
        for (Triple<Integer, String, Date> triple : triples) {
            System.out.println(triple);
        }
    }

}
