package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    private static TreeNode node;

	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        List<TreeNode> s1 = new ArrayList<>();
        List<TreeNode> s2 = new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        TreeNode c1 = root1;
        TreeNode c2 = root2;

        while(c1 != null || c2 != null || !s1.isEmpty() || !s2.isEmpty()) {
            while(c1 != null) {
                s1.add(c1);
                c1 = c1.left;
            }
            while(c2 != null) {
                s2.add(c2);
                c2 = c2.left;
            }

            if(!s1.isEmpty() && !s2.isEmpty()) {
                if(s1.get(s1.size() - 1).val < s2.get(s2.size() -1 ).val) {
                    TreeNode node = s1.remove(s1.size() - 1);
                    res.add(node.val);
                    c1 = node.right;
                } else {
                    TreeNode node = s2.remove(s2.size() - 1);
                    res.add(node.val);
                    c2 = node.right;
                }
            } else if(!s1.isEmpty()){
                TreeNode node = s1.remove(s1.size() - 1);
                res.add(node.val);
                c1 = node.right;
            } else if(!s2.isEmpty()){
                TreeNode node = s2.remove(s2.size() - 1);
                res.add(node.val);
                c2 = node.right;
            }
        }
        return res;
    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode constructTree(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
    
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
    
        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }
    
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (node == null) {
              output += "null, ";
              continue;
            }
    
            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }
    
        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    
        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);
    
        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node,  "", true);
    }

    public int sumRootToLeaf(TreeNode root) {
        return findSum(root, 0);
    }
    
    private int findSum(TreeNode root, int cs) {
        cs = (cs * 2) + root.val;
        System.out.println(cs);
        if(root.left == null && root.right == null) {
            System.out.println("LEAF =>" + cs);
            return cs;
        }
        return (root.left != null ? findSum(root.left, cs) : 0)
            + (root.right != null ? findSum(root.right, cs) : 0);
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return root;
        }
        if(val < root.val) {
            return searchBST(root.left, val);
        } else if(val > root.val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        TreeNode root = bt.constructTree("[4,2,7,1,3]");
        bt.prettyPrintTree(root);
        // System.out.println(bt.sumRootToLeaf(root));
        var node = bt.searchBST(root, 2);
        bt.prettyPrintTree(node);
        // System.out.println(bt.treeNodeToString(root));
    }
    
}
