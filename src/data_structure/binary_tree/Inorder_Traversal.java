package data_structure.binary_tree;

import data_structure.binary_tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 23:50
 */
public class Inorder_Traversal {
    public static void main(String[] args) {
        Inorder_Traversal app = new Inorder_Traversal();
        app.inorderTraversal(null);
    }

    public int[] inorderTraversal(TreeNode root) {
        return solution(root);
    }

    private int[] solution(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        inOrder(list, root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void inOrder(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(list, root.left);
        list.add(root.val);
        inOrder(list, root.right);
    }


}
