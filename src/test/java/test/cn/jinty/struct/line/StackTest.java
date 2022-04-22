package test.cn.jinty.struct.line;

import cn.jinty.struct.line.Stack;
import org.junit.Test;

/**
 * 栈 - 测试
 *
 * @author Jinty
 * @date 2021/6/11
 **/
public class StackTest {

    @Test
    public void test1() {
        Stack<Integer> stack = new Stack<>();
        test(stack);
    }

    @Test
    public void test2() {
        Stack<Integer> stack = new Stack<>(5, 5);
        test(stack);
    }

    private void test(Stack<Integer> stack) {
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
            System.out.print("stack push : " + i);
            System.out.print(", stack capacity : " + stack.capacity());
            System.out.print(", stack size : " + stack.size());
            System.out.println();
        }
        System.out.println("stack data : " + stack);
        while (!stack.isEmpty()) {
            System.out.print("stack peek : " + stack.peek());
            System.out.print(", stack pop : " + stack.pop());
            System.out.print(", stack capacity : " + stack.capacity());
            System.out.print(", stack size : " + stack.size());
            System.out.println();
        }
    }

}
