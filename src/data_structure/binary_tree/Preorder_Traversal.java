package data_structure.binary_tree;


import data_structure.binary_tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 23:30
 */
public class Preorder_Traversal {
    public static void main(String[] args) {
        Preorder_Traversal app = new Preorder_Traversal();
        app.preorderTraversal(null);
    }

    public int[] preorderTraversal(TreeNode root) {
        return solution(root);
    }

    /**
     * 利用栈
     * @param root
     * @return
     */
    private int[] solution(TreeNode root) {
        // 添加遍历结果的数组
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 空树返回空数组
        if (root == null) return new int[0];
        // 根节点先进栈
        stack.push(root);
        while (!stack.isEmpty()) {
            // 每次栈顶就是访问的元素
            TreeNode node = stack.pop();
            list.add(node.val);
            // 如果右边还有右子节点进栈 先进后出
            if (node.right != null) {
                stack.push(node.right);
            }
            // 如果左边还有左子节点进栈
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        // 返回的结果
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    private int[] solution1(TreeNode root) {
        // 添加遍历结果的数组
        List<Integer> list = new ArrayList<>();
        // 递归前序遍历
        preorder(list, root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void preorder(List<Integer> list, TreeNode root) {
        // 遇到空节点则返回
        if (root == null) {
            return;
        }
        // 先遍历根节点
        list.add(root.val);
        // 再去左子树
        preorder(list, root.left);
        // 最后去右子树
        preorder(list, root.right);

    }

}
