package test.cn.jinty.ruleengine.easyrule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.junit.Test;
import org.mvel2.MVEL;

/**
 * 规则引擎 - EasyRule - 测试
 *
 * @author Jinty
 * @date 2023/9/4
 **/
public class EasyRuleTest {

    // 1、通过一个带注解的类定义规则
    @Test
    public void test1() {
        TemperatureRule rule = new TemperatureRule();
        Rules rules = new Rules();
        rules.register(rule);
        matchRule(rule);
    }

    // 2、通过RuleBuilder构建的对象定义规则
    @Test
    public void test2() {
        Rule rule = new RuleBuilder()
                .name("TemperatureRule")
                .description("温度预警")
                .when(facts -> Double.valueOf(35.0).compareTo(facts.get("degree")) < 0)
                .then(facts -> {
                    try {
                        this.action(facts);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .build();
        matchRule(rule);
    }

    // 3、通过MVEL表达式构建的对象定义规则
    // 将这几个关键字段的字符串值，从界面上传入，将基本逻辑公开给最终用户，就可以实现不发布上线而实时更新系统的逻辑
    // 输入数据如何关联这个规则？可以给每个规则定义一个全局唯一的ID(不可修改)，通过关联不同的ID来选择使用不同的规则
    @Test
    public void test3() {
        System.out.println(MVEL.NAME);
        Rule rule = new MVELRule()
                .name("TemperatureRule")
                .description("温度预警")
                .when("degree > 35.0")
                .then("System.out.printf(\"当前温度[%s]，高温预警，谨防中暑%n\", degree);");
        matchRule(rule);
    }

    // 4、通过RuleBuilder构建的对象定义规则，条件语句使用MVEL表达式
    @Test
    public void test4() {
        Rule rule = new RuleBuilder()
                .name("TemperatureRule")
                .description("温度预警")
                .when(facts -> MVEL.evalToBoolean("degree > 35.0", facts.asMap()))
                .then(facts -> {
                    try {
                        this.action(facts);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .build();
        matchRule(rule);
    }

    /* 以下为内部函数 */

    // 匹配规则
    private void matchRule(Object rule) {
        // 注册规则
        Rules rules = new Rules();
        rules.register(rule);

        // 输入数据
        Facts facts = new Facts();
        facts.put("degree", 38.5);

        // 匹配数据及规则，得出结论
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }

    // 执行动作
    private void action(Facts facts) {
        // 下面这种写法会抛出ClassCastException: java.lang.Double cannot be cast to [Ljava.lang.Object
        // 原因是facts.get的返回类型是泛型，而String.format的第二个参数是不定参数，所以Object转List<Object>，类型转换异常
        /*String s = String.format("当前温度[%s]，高温预警，谨防中暑", facts.get("degree"));
        System.out.println(s);*/
        String s = String.format("当前温度[%s]，高温预警，谨防中暑", (Object) facts.get("degree"));
        System.out.println(s);
    }

}
