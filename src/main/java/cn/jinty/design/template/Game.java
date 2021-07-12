package cn.jinty.design.template;

/**
 * 游戏 - 抽象类 - 模板方法模式
 *
 * @author jinty
 * @date 2021/7/8
 **/
public abstract class Game {

    //开启
    protected abstract void startup();

    //玩
    protected abstract void playing();

    //关闭
    protected abstract void shutdown();

    //模板方法
    public final void play(){
        startup();
        playing();
        shutdown();
    }

}
