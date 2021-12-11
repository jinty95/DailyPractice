package test.cn.jinty.leetcode.entity;

import cn.jinty.leetcode.entity.TopVotedCandidate;
import org.junit.Test;

/**
 * 在线选举 - 测试
 *
 * @author Jinty
 * @date 2021/12/11
 **/
public class TopVotedCandidateTest {

    @Test
    public void test() {
        TopVotedCandidate vote = new TopVotedCandidate(
                new int[]{0, 1, 0, 1, 0, 1, 0, 1},
                new int[]{0, 5, 10, 15, 20, 25, 30, 35}
        );
        System.out.println(vote.q(3));
        System.out.println(vote.q(8));
        System.out.println(vote.q(19));
        System.out.println(vote.q(24));
        System.out.println(vote.q(35));
    }

}
