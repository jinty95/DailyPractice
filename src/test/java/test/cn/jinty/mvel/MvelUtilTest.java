package test.cn.jinty.mvel;

import cn.jinty.mvel.MvelUtil;
import cn.jinty.mvel.entity.Condition;
import cn.jinty.mvel.enums.LogicOperatorEnum;
import cn.jinty.mvel.enums.OperationOperatorEnum;
import cn.jinty.mvel.enums.OperatorTypeEnum;
import org.junit.Test;
import org.mvel2.MVEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVEL - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/9/6
 **/
public class MvelUtilTest {

    // 第一次解析运行耗时比较久，后续都比较快
    // 数字与字符串可以直接比较，不会受类型影响，只比较数值
    // 如果要在表达式中表示字符串，需要加""，否则字符串会被当成变量处理
    @Test
    public void test() {
        eval(genExp1());
        System.out.println();
        eval(genExp2());
    }

    /* 以下为内部函数 */

    private void eval(String exp) {
        System.out.println("表达式：" + exp);
        Map<String, Object> input = new HashMap<>();
        for (int i = 1; i <= 20; i++) {
            long begin = System.currentTimeMillis();
            input.put("age", i);
            System.out.println("输入：" + input);
            System.out.println("输出：" + MVEL.evalToBoolean(exp, input));
            System.out.println("耗时：" + (System.currentTimeMillis() - begin) + "ms");
        }
    }

    private String genExp1() {
        Condition condition = new Condition();
        condition.setOperatorType(OperatorTypeEnum.LOGIC.getCode());
        condition.setOperator(LogicOperatorEnum.AND.getCode());
        condition.setChildren(genChildren());
        return MvelUtil.genExp(condition);
    }

    private String genExp2() {
        List<Condition> children = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setOperatorType(OperatorTypeEnum.LOGIC.getCode());
        condition1.setOperator(LogicOperatorEnum.AND.getCode());
        condition1.setChildren(genChildren());
        children.add(condition1);
        Condition condition = new Condition();
        condition.setOperatorType(OperatorTypeEnum.LOGIC.getCode());
        condition.setOperator(LogicOperatorEnum.NOT.getCode());
        condition.setChildren(children);
        return MvelUtil.genExp(condition);
    }

    private List<Condition> genChildren() {
        List<Condition> children = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition1.setOperator(OperationOperatorEnum.GREATER_THAN.getCode());
        condition1.setMetricName("age");
        condition1.setMetricValue("8");
        Condition condition2 = new Condition();
        condition2.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition2.setOperator(OperationOperatorEnum.LESS_THAN.getCode());
        condition2.setMetricName("age");
        condition2.setMetricValue("15");
        Condition condition3 = new Condition();
        condition3.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition3.setOperator(OperationOperatorEnum.NOT_EQUALS.getCode());
        condition3.setMetricName("age");
        condition3.setMetricValue("12");
        Condition condition4 = new Condition();
        condition4.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition4.setOperator(OperationOperatorEnum.CONTAINS.getCode());
        condition4.setMetricName("age");
        condition4.setMetricValue("[9,10]");
        children.add(condition1);
        children.add(condition2);
        children.add(condition3);
        children.add(condition4);
        return children;
    }

}
