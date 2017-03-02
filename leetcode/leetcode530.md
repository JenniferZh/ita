##[Leetcode 530] Minimum Absolute Difference

##题目

>求一颗BST中差最小的两个节点的差的绝对值

##思路

>**BST的中序遍历元素是有序的，最小的差值一定是遍历中相邻的两个节点**

##程序

    public class Solution {
	    TreeNode prev = null;
	    int mindiff = Integer.MAX_VALUE;
    
	    public int getMinimumDifference(TreeNode root) {
	        inorder(root);
	        return mindiff;
	    }
    
	    public void inorder(TreeNode root) {
	        if(root == null) return;
	        inorder(root.left);
        
	        if(prev != null) mindiff = Math.min(mindiff, root.val-prev.val);
	        prev = root;
        
	        inorder(root.right);
	    }
	}




> Written with [StackEdit](https://stackedit.io/).