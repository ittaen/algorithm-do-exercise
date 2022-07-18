package recursion;

import java.util.Stack;

/**
 * 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/17 18:18
 */
public class Reverse_Stack_Using_Recursive {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 10; i > 0; i--) {
            stack.push(i);
        }
        stack.forEach(System.out::println);
        reverse(stack);
        stack.forEach(System.out::println);
    }

    private static void reverse(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        int last = getLastAndDown(stack);
        reverse(stack);
        stack.push(last);
    }

    private static int getLastAndDown(Stack<Integer> stack) {
        Integer element = stack.pop();
        if (stack.isEmpty()) {
            return element;
        } else {
            int last = getLastAndDown(stack);
            stack.push(element);
            return last;
        }
    }


}
