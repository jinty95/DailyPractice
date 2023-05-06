package cn.jinty.game;

import java.math.BigDecimal;
import java.util.*;

/**
 * 幸运抽奖
 *
 * @author Jinty
 * @date 2023/5/6
 **/
public class LuckyDraw {

    // 奖品及其概率
    private final Map<String, BigDecimal> prizeAndProbability = new HashMap<>();

    // 累进概率及其奖品
    private TreeMap<BigDecimal, String> progressionProbabilityAndPrize;

    /**
     * 查看奖品池
     *
     * @return 奖品池
     */
    public Map<String, BigDecimal> showPrize() {
        // 不要直接暴露，避免在外部被修改
        return Collections.unmodifiableMap(prizeAndProbability);
    }

    /**
     * 设置奖品
     *
     * @param prize       奖品
     * @param probability 概率(限制0-1小数)
     */
    public void setPrize(String prize, BigDecimal probability) {
        // 奖品不能为空
        if (prize == null) {
            throw new IllegalArgumentException("prize cannot be null");
        }
        if (probability == null) {
            probability = BigDecimal.ZERO;
        }
        // 概率必须是[0, 100]
        if (probability.compareTo(BigDecimal.ZERO) < 0 || probability.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("probability must be in [0, 1]");
        }
        // 总概率不能超出1
        BigDecimal sumProbability = BigDecimal.ZERO;
        for (String key : prizeAndProbability.keySet()) {
            if (!key.equals(prize)) {
                sumProbability = sumProbability.add(prizeAndProbability.get(key));
            }
        }
        sumProbability = sumProbability.add(probability);
        if (sumProbability.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("sumProbability cannot great than 1");
        }
        // 奖品放入抽奖池
        prizeAndProbability.put(prize, probability);
        // 构建累进概率
        buildProgressionProbability();
    }

    /**
     * 移除奖品
     *
     * @param prize 奖品
     */
    public void removePrize(String prize) {
        // 移除奖品
        prizeAndProbability.remove(prize);
        // 构建累进概率
        buildProgressionProbability();
    }

    /**
     * 抽取奖品
     *
     * @return 奖品
     */
    public String getPrize() {
        // 生成[0,1)的随机数
        BigDecimal draw = BigDecimal.valueOf(Math.random());
        BigDecimal ceilingKey = progressionProbabilityAndPrize.ceilingKey(draw);
        return ceilingKey == null ? null : progressionProbabilityAndPrize.get(ceilingKey);
    }

    /* 以下为内部函数 */

    /**
     * 构建累进概率
     */
    private void buildProgressionProbability() {
        progressionProbabilityAndPrize = new TreeMap<>();
        BigDecimal progression = BigDecimal.ZERO;
        for (String key : prizeAndProbability.keySet()) {
            BigDecimal probability = prizeAndProbability.get(key);
            // 概率为0直接忽略
            if (probability.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            progression = progression.add(probability);
            progressionProbabilityAndPrize.put(progression, key);
        }
    }

}
