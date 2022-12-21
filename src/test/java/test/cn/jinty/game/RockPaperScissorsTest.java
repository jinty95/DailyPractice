package test.cn.jinty.game;

import cn.jinty.game.RockPaperScissors;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 剪刀石头布 - 测试
 *
 * @author Jinty
 * @date 2022/12/21
 **/
public class RockPaperScissorsTest {

    // 胜负平的频率
    @Test
    public void testRun() {
        RockPaperScissors game = new RockPaperScissors();
        int[] count = new int[3];
        int total = 30000;
        for (int i = 0; i < total; i++) {
            int[] data = game.run();
            count[data[2]]++;
        }
        System.out.println("total game count : " + total);
        for (int i = 0; i < count.length; i++) {
            System.out.println(RockPaperScissors.RESULTS[i] + " count : " + count[i]);
        }
    }

    // 游戏结果可视化
    @Test
    public void testRun1() {
        RockPaperScissors game = new RockPaperScissors();
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(game.visualize(game.run())));
        }
    }

    // 三次游戏为一组，p1三次都赢的频率
    @Test
    public void testRun2() {
        RockPaperScissors game = new RockPaperScissors();
        int count = 10000, winCount = 0;
        for (int i = 0; i < count; i++) {
            if (game.run()[2] == 1 && game.run()[2] == 1 && game.run()[2] == 1) {
                winCount++;
            }
        }
        double frequency = winCount * 1.0 / count;
        System.out.println("count : " + count + ", winCount : " + winCount + ", frequency : " + frequency);
    }

    // 非公平条件下胜负平的频率
    @Test
    public void testRunUnfairly() {
        RockPaperScissors game = new RockPaperScissors();
        for (int bias = 0; bias <= 2; bias++) {
            int[] count = new int[3];
            int total = 30000;
            for (int i = 0; i < total; i++) {
                int[] data = game.runUnfairly(bias);
                count[data[2]]++;
            }
            System.out.println("total unfair game count : " + total + ", bias : " + bias);
            for (int i = 0; i < count.length; i++) {
                System.out.println(RockPaperScissors.RESULTS[i] + " count : " + count[i]);
            }
            System.out.println();
        }
    }

    // 游戏结果可视化
    @Test
    public void testRunUnfairly1() {
        RockPaperScissors game = new RockPaperScissors();
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(game.visualize(game.runUnfairly(2))));
        }
    }

    // 人工输入与电脑博弈
    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        System.out.println("游戏：石头剪刀布");
        System.out.println("规则：与电脑猜拳，获胜则积分+3，平局则积分+1，失败则积分不变");
        System.out.println("说明：请输入一个数字，代表您的选择，0-石头，1-布，2-剪刀");
        Scanner scanner = new Scanner(System.in);
        int count = 3, winCount = 0, loseCount = 0, drawCount = 0;
        int point = 0;
        for (int i = 0; i < count; i++) {
            int p1 = scanner.nextInt();
            int[] res = game.run(p1);
            System.out.println("您的选择：" + RockPaperScissors.CHOICES_CN[res[0]]);
            System.out.println("电脑的选择：" + RockPaperScissors.CHOICES_CN[res[1]]);
            if (res[2] == 0) {
                drawCount++;
                point += 1;
                System.out.println(RockPaperScissors.RESULTS_CN[0]);
            } else if (res[2] == 1) {
                winCount++;
                point += 3;
                System.out.println("恭喜您，获胜了！");
            } else if (res[2] == 2) {
                loseCount++;
                System.out.println("很遗憾，您输了！");
            }
        }
        System.out.println();
        System.out.printf("局数%s 胜%s平%s负%s 积分%s%n", count, winCount, drawCount, loseCount, point);
    }

}
