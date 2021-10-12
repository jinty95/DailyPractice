package cn.jinty.design.behavior.command;

/**
 * 命令 - 买股票
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class StockBuy implements Order {

    private final Stock stock = new Stock();

    @Override
    public void execute() {
        stock.buy();
    }
}
