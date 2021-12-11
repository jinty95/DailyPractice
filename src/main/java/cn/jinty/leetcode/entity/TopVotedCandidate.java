package cn.jinty.leetcode.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 在线选举
 *
 * @author Jinty
 * @date 2021/12/11
 **/
public class TopVotedCandidate {

    // 时刻 -> 获胜者
    private final TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    /**
     * 在线选举：在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
     *
     * @param persons 候选人
     * @param times 时刻(严格递增)
     */
    public TopVotedCandidate(int[] persons, int[] times) {
        // 候选人 -> 票数
        Map<Integer, Integer> map = new HashMap<>();
        // 最大票数
        int maxVote = 0;
        // 获胜者
        int winner = -1;
        // 遍历投票过程，实时刷新票数、最大票数、获胜者
        for (int i = 0; i < times.length; i++) {
            int curVote = map.getOrDefault(persons[i], 0) + 1;
            map.put(persons[i], curVote);
            if (curVote >= maxVote) {
                maxVote = curVote;
                winner = persons[i];
            }
            treeMap.put(times[i], winner);
        }
    }

    /**
     * 返回在 t 时刻的获胜者
     *
     * @param t 时刻
     * @return 获胜者
     */
    public int q(int t) {
        return treeMap.get(treeMap.floorKey(t));
    }

}
