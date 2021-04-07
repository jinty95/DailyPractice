package cn.jinty.design.command;

/**
 * 命令 - 卖股票
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class StockSell implements Order{

    private Stock stock = new Stock();

    @Override
    public void execute() {
        stock.sell();
    }
}
