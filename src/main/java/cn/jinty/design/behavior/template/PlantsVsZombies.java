package cn.jinty.design.behavior.template;

/**
 * 植物大战僵尸 - 游戏 - 实现类
 *
 * @author jinty
 * @date 2021/7/8
 **/
public class PlantsVsZombies extends Game {

    private final static String name = PlantsVsZombies.class.getSimpleName();

    @Override
    protected void startup() {
        System.out.printf("[%s] startup %n", name);
    }

    @Override
    protected void playing() {
        System.out.printf("[%s] playing, so excited %n", name);
    }

    @Override
    protected void shutdown() {
        System.out.printf("[%s] shutdown %n", name);
    }

}
