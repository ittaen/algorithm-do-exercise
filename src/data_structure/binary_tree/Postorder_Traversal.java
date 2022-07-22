package data_structure.binary_tree;

import data_structure.binary_tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 23:56
 */
public class Postorder_Traversal {
    public static void main(String[] args) {
        Postorder_Traversal app = new Postorder_Traversal();
        app.postorderTraversal(null);
    }

    public int[] postorderTraversal (TreeNode root) {
        return solution(root);
    }

    private int[] solution(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> list = new ArrayList<>();
        postOrder(list, root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void postOrder(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(list, root.left);
        postOrder(list, root.right);
        list.add(root.val);
    }
}
