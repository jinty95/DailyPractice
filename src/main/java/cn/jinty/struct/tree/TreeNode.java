package cn.jinty.struct.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树节点
 *
 * @author jinty
 * @date 2020/12/25
 **/
public class TreeNode {

    //值
    public int val;
    //左子树
    public TreeNode left;
    //右子树
    public TreeNode right;

    //构造器
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    //前序遍历
    public List<Integer> preOrder(){
        return preOrder(this);
    }
    public static List<Integer> preOrder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        preOrder(root,list,true);
        return list;
    }
    public static void preOrder(TreeNode root, List<Integer> list, boolean containNull){
        if(root==null){
            if(containNull) list.add(null);
        }else{
            list.add(root.val);
            preOrder(root.left,list,containNull);
            preOrder(root.right,list,containNull);
        }
    }

    //中序遍历
    public List<Integer> inOrder(){
        return inOrder(this);
    }
    public static List<Integer> inOrder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        inOrder(root,list,true);
        return list;
    }
    public static void inOrder(TreeNode root, List<Integer> list, boolean containNull){
        if(root==null){
            if(containNull) list.add(null);
        }else{
            inOrder(root.left,list,containNull);
            list.add(root.val);
            inOrder(root.right,list,containNull);
        }
    }

    //后序遍历
    public List<Integer> postOrder(){
        return postOrder(this);
    }
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        postOrder(root,list,true);
        return list;
    }
    public static void postOrder(TreeNode root, List<Integer> list, boolean containNull){
        if(root==null){
            if(containNull) list.add(null);
        }else{
            postOrder(root.left,list,containNull);
            postOrder(root.right,list,containNull);
            list.add(root.val);
        }
    }

    //层次遍历
    public List<Integer> bfs(){
        return bfs(this);
    }
    public static List<Integer> bfs(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if(node!=null){
                    list.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }else{
                    list.add(null);
                }
            }
        }
        return list;
    }

    //二叉树序列化为字符串
    public String serialize(){
        return serialize(this);
    }
    public static String serialize(TreeNode root) {
        if(root==null) return null;
        return bfs(root).toString();
    }

    //从字符串反序列化出二叉树
    public static TreeNode deserialize(String data) {
        if(data==null || data.length()==0) return null;
        //1、解析序列字符串
        data = data.substring(1,data.length()-1);
        String[] arr = data.split(",");
        for(int i=0; i<arr.length; i++){
            arr[i] = arr[i].trim();
        }
        //2、重构二叉树
        if(arr[0].equals("null")) return null;
        //根节点
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        //保存每一层的非空节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //标识数组中的可用位置
        int idx = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                String leftVal = arr[idx++];
                String rightVal = arr[idx++];
                if(!leftVal.equals("null")){
                    TreeNode leftNode = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(leftNode);
                    node.left = leftNode;
                }
                if(!rightVal.equals("null")){
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
