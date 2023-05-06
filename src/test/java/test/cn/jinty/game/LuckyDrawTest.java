package test.cn.jinty.game;

import cn.jinty.game.LuckyDraw;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * 幸运抽奖 - 测试
 *
 * @author Jinty
 * @date 2023/5/6
 **/
public class LuckyDrawTest {

    private LuckyDraw buildLuckyDraw() {
        LuckyDraw luckyDraw = new LuckyDraw();
        for (char c = 'A'; c <= 'Z'; c++) {
            luckyDraw.setPrize(String.valueOf(c), BigDecimal.ONE.divide(BigDecimal.valueOf('Z' - 'A' + 1), 7, RoundingMode.FLOOR));
        }
        luckyDraw.removePrize(null);
        luckyDraw.removePrize("A");
        luckyDraw.showPrize().entrySet().forEach(System.out::println);
        return luckyDraw;
    }

    @Test
    public void testGetPrize() {
        LuckyDraw luckyDraw = buildLuckyDraw();
        Map<String, Integer> prizeAndCount = new HashMap<>();
        for (int i = 1; i <= 1000000; i++) {
            String prize = luckyDraw.getPrize();
            prizeAndCount.put(prize, prizeAndCount.getOrDefault(prize, 0) + 1);
        }
        System.out.println();
        prizeAndCount.entrySet().forEach(System.out::println);
    }

}
