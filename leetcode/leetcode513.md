
##[Leetcode 513] Find Bottom Left Tree Value

##题目

>计算树最后一层中的最左一个节点

##思路
>**BFS**
>注意Queue接口里有什么函数
>注意栈中每次保留一层元素的写法


##程序

    public class Solution {
	    int curcnt = 1;
    
	    public int findBottomLeftValue(TreeNode root) {
        
	        Queue<TreeNode> queue = new LinkedList<>();
	        LinkedList<TreeNode> result = new LinkedList<>();
        
	        queue.add(root);
	        while(!queue.isEmpty()) {
	            result.add(queue.peek());
	            for(int i = 0; i < curcnt; i++) {
	                TreeNode tmp = queue.remove();
	                if(tmp.left != null) queue.add(tmp.left);
	                if(tmp.right != null) queue.add(tmp.right);
	            }
	            curcnt = queue.size();
	        }
        
	        return result.getLast().val;
	    }
    
	}

> Written with [StackEdit](https://stackedit.io/).