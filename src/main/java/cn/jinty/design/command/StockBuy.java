package cn.jinty.design.command;

/**
 * 命令 - 买股票
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class StockBuy implements Order{

    private Stock stock = new Stock();

    @Override
    public void execute() {
        stock.buy();
    }
}
