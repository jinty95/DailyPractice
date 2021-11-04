package test.cn.jinty.design.behavior.template;

import cn.jinty.design.behavior.template.AngryBird;
import cn.jinty.design.behavior.template.Game;
import cn.jinty.design.behavior.template.PlantsVsZombies;

/**
 * 模板方法模式 - 测试
 *
 * @author Jinty
 * @date 2021/7/8
 **/
public class Test {

    public static void main(String[] args) {
        Game g1 = new AngryBird();
        g1.play();
        System.out.println();
        Game g2 = new PlantsVsZombies();
        g2.play();
    }

}
