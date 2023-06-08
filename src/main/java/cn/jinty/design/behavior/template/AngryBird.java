package cn.jinty.design.behavior.template;

/**
 * 愤怒的小鸟 - 游戏 - 实现类
 *
 * @author Jinty
 * @date 2021/7/8
 **/
public class AngryBird extends Game {

    private static final String name = AngryBird.class.getSimpleName();

    @Override
    protected void startup() {
        System.out.printf("[%s] startup %n", name);
    }

    @Override
    protected void playing() {
        System.out.printf("[%s] playing, so funny %n", name);
    }

    @Override
    protected void shutdown() {
        System.out.printf("[%s] shutdown %n", name);
    }

}
