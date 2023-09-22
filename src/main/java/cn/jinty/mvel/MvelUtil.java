package cn.jinty.mvel;

import cn.jinty.mvel.entity.Condition;
import cn.jinty.mvel.enums.LogicOperatorEnum;
import cn.jinty.mvel.enums.OperationOperatorEnum;
import cn.jinty.mvel.enums.OperatorTypeEnum;

import java.util.List;

/**
 * MVEL - 工具类
 * MVFLEX Expression Language，一种表达式语言
 *
 * @author Jinty
 * @date 2023/9/6
 **/
public final class MvelUtil {

    private MvelUtil() {
    }

    private static final String EMPTY = "";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";

    /**
     * 根据结构化的条件对象生成表达式
     *
     * @param condition 结构化的条件对象
     * @return 表达式
     */
    public static String genExp(Condition condition) {
        if (condition == null) {
            return EMPTY;
        }
        StringBuilder exp = new StringBuilder();
        String operatorType = condition.getOperatorType();
        if (OperatorTypeEnum.OPERATION.getCode().equals(operatorType)) {
            exp.append(genOperation(condition.getOperator(), condition.getMetricName(), condition.getMetricValue()));
        } else if (OperatorTypeEnum.LOGIC.getCode().equals(operatorType)) {
            List<Condition> children = condition.getChildren();
            if (children == null || children.isEmpty()) {
                return EMPTY;
            }
            String subExp = EMPTY;
            for (Condition cnd : children) {
                subExp = genLogic(condition.getOperator(), subExp, LEFT_BRACKET + genExp(cnd) + RIGHT_BRACKET);
            }
            if (!EMPTY.equals(subExp)) {
                subExp = LEFT_BRACKET + subExp + RIGHT_BRACKET;
            }
            exp.append(subExp);
        }
        return exp.toString();
    }

    /* 以下为内部函数 */

    /**
     * 生成运算表达式
     *
     * @param operator    运算操作符
     * @param metricName  指标名
     * @param metricValue 指标值
     * @return 运算表达式
     */
    private static String genOperation(String operator, String metricName, String metricValue) {
        OperationOperatorEnum operationOperatorEnum = OperationOperatorEnum.parseByCode(operator);
        if (operationOperatorEnum == null) {
            return EMPTY;
        }
        if (operationOperatorEnum == OperationOperatorEnum.CONTAINS ||
                operationOperatorEnum == OperationOperatorEnum.NOT_CONTAINS) {
            return String.format(operationOperatorEnum.getExpression(), metricValue, metricName);
        }
        return String.format(operationOperatorEnum.getExpression(), metricName, metricValue);
    }

    /**
     * 生成逻辑表达式
     *
     * @param operator 逻辑操作符
     * @param left     左条件
     * @param right    右条件
     * @return 逻辑表达式
     */
    private static String genLogic(String operator, String left, String right) {
        LogicOperatorEnum logicOperatorEnum = LogicOperatorEnum.parseByCode(operator);
        if (logicOperatorEnum == null) {
            return EMPTY;
        }
        // 特殊处理第一个子条件
        if (left == null || left.isEmpty()) {
            if (logicOperatorEnum == LogicOperatorEnum.NOT) {
                return String.format(LogicOperatorEnum.NOT.getExpression(), right);
            }
            return right;
        }
        // 非逻辑只关注第一个子条件
        if (logicOperatorEnum == LogicOperatorEnum.NOT) {
            return left;
        }
        return String.format(logicOperatorEnum.getExpression(), left, right);
    }

}
