package cn.jinty.design.behavior.command;

/**
 * 命令 - 卖股票
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class StockSell implements Order {

    private final Stock stock = new Stock();

    @Override
    public void execute() {
        stock.sell();
    }
}
