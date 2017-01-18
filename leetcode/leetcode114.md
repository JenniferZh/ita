
##[Leetcode 114 ]Flatten Binary Tree to Linked List

##题目

>Given a binary tree, flatten it to a linked list in-place.
>For example,
>Given

         1
        / \
       2   5
      / \   \
     3   4   6
>The flattened tree should look like:
       

         1 
          \
           2
            \
             3
              \
               4
                \
                 5
                  \
                   6


##思路
>递归，整棵树变平的方法是把左子树变平挂在右子树上，把原来的右子树变平挂在现在的右子树下面，在把左子树置为空。

##code

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution {
        public void flatten(TreeNode root) {
            flattenhelper(root);
        }
        
        public TreeNode flattenhelper(TreeNode root) {
            if(root == null) return root;
            TreeNode tmp = root.right;
            root.right = flattenhelper(root.left);
            root.left = null;
            TreeNode p = root;
            while(p.right != null) p = p.right;
            p.right = flattenhelper(tmp);
            return root;
        }
    }

> Written with [StackEdit](https://stackedit.io/).