package cn.jinty.struct.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树 - 节点
 *
 * @author Jinty
 * @date 2020/12/25
 **/
@SuppressWarnings("unused")
public class TreeNode {

    //值
    public int val;
    //左子树
    public TreeNode left;
    //右子树
    public TreeNode right;

    //构造器
    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    //前序遍历
    public List<Integer> preorder() {
        return preorder(this);
    }

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list, true);
        return list;
    }

    public static void preorder(TreeNode root, List<Integer> list, boolean containNull) {
        if (root == null) {
            if (containNull) list.add(null);
        } else {
            list.add(root.val);
            preorder(root.left, list, containNull);
            preorder(root.right, list, containNull);
        }
    }

    //中序遍历
    public List<Integer> inorder() {
        return inorder(this);
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list, true);
        return list;
    }

    public static void inorder(TreeNode root, List<Integer> list, boolean containNull) {
        if (root == null) {
            if (containNull) list.add(null);
        } else {
            inorder(root.left, list, containNull);
            list.add(root.val);
            inorder(root.right, list, containNull);
        }
    }

    //后序遍历
    public List<Integer> postorder() {
        return postorder(this);
    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list, true);
        return list;
    }

    public static void postorder(TreeNode root, List<Integer> list, boolean containNull) {
        if (root == null) {
            if (containNull) list.add(null);
        } else {
            postorder(root.left, list, containNull);
            postorder(root.right, list, containNull);
            list.add(root.val);
        }
    }

    //层次遍历
    public List<Integer> bfs() {
        return bfs(this);
    }

    public static List<Integer> bfs(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    list.add(null);
                }
            }
        }
        return list;
    }

    //二叉树序列化为字符串
    public String serialize() {
        return serialize(this);
    }

    public static String serialize(TreeNode root) {
        if (root == null) return null;
        return bfs(root).toString();
    }

    //从字符串反序列化出二叉树：字符串格式为[1,2,3,4,...]，空节点必须填写且值为"null"
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals("[]")) return null;
        //1、解析序列字符串
        data = data.substring(1, data.length() - 1);
        String[] arr = data.split(",");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        //2、重构二叉树
        if (arr[0].equals("null") || arr[0].equals("")) return null;
        //根节点
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        //保存每一层的非空节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //标识数组中的可用位置
        int idx = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                String leftVal = arr[idx++];
                String rightVal = arr[idx++];
                if (!leftVal.equals("null")) {
                    TreeNode leftNode = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(leftNode);
                    node.left = leftNode;
                }
                if (!rightVal.equals("null")) {
                    TreeNode rightNode = new TreeNode(Integer.parseInt(rightVal));
                    queue.offer(rightNode);
                    node.right = rightNode;
                }
            }
        }
        return root;
    }

    //转为字符串
    @Override
    public String toString() {
        return serialize(this);
    }

}
