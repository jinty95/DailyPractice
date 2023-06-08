package cn.jinty.game;

import java.util.Random;

/**
 * 剪刀石头布
 *
 * @author Jinty
 * @date 2022/12/21
 **/
public class RockPaperScissors {

    // 随机数
    private static final Random RANDOM = new Random();

    // 猜拳可选项
    public static final String[] CHOICES = new String[]{"rock", "paper", "scissors"};
    public static final String[] CHOICES_CN = new String[]{"石头", "布", "剪刀"};

    // 结果
    public static final String[] RESULTS = new String[]{"draw game", "winner p1", "winner p2"};
    public static final String[] RESULTS_CN = new String[]{"平局", "玩家1获胜", "玩家2获胜"};

    /**
     * 进行一次游戏 (公平)
     *
     * @return 游戏结果
     */
    public int[] run() {
        // p1出拳
        int p1 = RANDOM.nextInt(3);
        return run(p1);
    }

    /**
     * 进行一次游戏 (公平)
     *
     * @param p1 p1出拳
     * @return 游戏结果
     */
    public int[] run(int p1) {
        if (p1 < 0 || p1 > 2) {
            throw new IllegalArgumentException("input must be in [0, 1, 2]");
        }
        // p2出拳
        int p2 = RANDOM.nextInt(3);
        // 结果：0表示平局，1表示p1赢，2表示p2赢
        int res = 0;
        if (p1 != p2) {
            if ((p1 == 1 && p2 == 0) || (p1 == 2 && p2 == 1) || (p1 == 0 && p2 == 2)) {
                res = 1;
            } else {
                res = 2;
            }
        }
        return new int[]{p1, p2, res};
    }

    /**
     * 进行一次游戏 (不公平)
     *
     * @param bias 偏向 (0表示平局，1表示p1赢，2表示p2赢)
     * @return 游戏结果
     */
    public int[] runUnfairly(int bias) {
        // p1出拳
        int p1 = RANDOM.nextInt(3);
        return runUnfairly(bias, p1);
    }

    /**
     * 进行一次游戏 (不公平)
     *
     * @param bias 偏向 (0表示平局，1表示p1赢，2表示p2赢)
     * @param p1   p1出拳
     * @return 游戏结果
     */
    public int[] runUnfairly(int bias, int p1) {
        if (p1 < 0 || p1 > 2) {
            throw new IllegalArgumentException("input must be in [0, 1, 2]");
        }
        if (bias == 0) {
            return new int[]{p1, p1, 0};
        } else if (bias == 1) {
            int p2 = (p1 + 3 - 1) % 3;
            return new int[]{p1, p2, 1};
        } else if (bias == 2) {
            int p2 = (p1 + 1) % 3;
            return new int[]{p1, p2, 2};
        }
        return run();
    }

    /**
     * 将游戏结果可视化
     *
     * @param res 游戏结果
     * @return 可视化结果
     */
    public String[] visualize(int[] res) {
        String[] visible = new String[3];
        visible[0] = "p1 choice : " + CHOICES[res[0]];
        visible[1] = "p2 choice : " + CHOICES[res[1]];
        visible[2] = "result : " + RESULTS[res[2]];
        return visible;
    }

}
