package test.cn.jinty.ruleengine.easyrule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

/**
 * 温度规则
 *
 * @author Jinty
 * @date 2023/9/4
 **/
@Rule(name = "TemperatureRule", description = "温度预警")
public class TemperatureRule {

    @Condition
    public boolean highTemp(@Fact("degree") double degree) {
        return degree > 35.0;
    }

    @Action
    public void sendAlarm(@Fact("degree") double degree) {
        System.out.printf("当前温度[%s]，高温预警，谨防中暑%n", degree);
    }

}
