package test.cn.jinty.mvel;

import cn.jinty.mvel.MvelUtil;
import cn.jinty.mvel.entity.Condition;
import cn.jinty.mvel.enums.LogicOperatorEnum;
import cn.jinty.mvel.enums.OperationOperatorEnum;
import cn.jinty.mvel.enums.OperatorTypeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * MVEL - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/9/6
 **/
public class MvelUtilTest {

    @Test
    public void test1() {
        Condition condition = new Condition();
        condition.setOperatorType(OperatorTypeEnum.LOGIC.getCode());
        condition.setOperator(LogicOperatorEnum.AND.getCode());
        condition.setChildren(getChildren());
        System.out.println(MvelUtil.genMvel(condition));
    }

    @Test
    public void test2() {
        List<Condition> children = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setOperatorType(OperatorTypeEnum.LOGIC.getCode());
        condition1.setOperator(LogicOperatorEnum.AND.getCode());
        condition1.setChildren(getChildren());
        children.add(condition1);
        Condition condition = new Condition();
        condition.setOperatorType(OperatorTypeEnum.LOGIC.getCode());
        condition.setOperator(LogicOperatorEnum.NOT.getCode());
        condition.setChildren(children);
        System.out.println(MvelUtil.genMvel(condition));
    }

    private List<Condition> getChildren() {
        List<Condition> children = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition1.setOperator(OperationOperatorEnum.GREATER_THAN.getCode());
        condition1.setMetricName("age");
        condition1.setMetricValue("18");
        Condition condition2 = new Condition();
        condition2.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition2.setOperator(OperationOperatorEnum.LESS_THAN.getCode());
        condition2.setMetricName("age");
        condition2.setMetricValue("45");
        Condition condition3 = new Condition();
        condition3.setOperatorType(OperatorTypeEnum.OPERATION.getCode());
        condition3.setOperator(OperationOperatorEnum.NOT_EQUALS.getCode());
        condition3.setMetricName("age");
        condition3.setMetricValue("38");
        children.add(condition1);
        children.add(condition2);
        children.add(condition3);
        return children;
    }

}
