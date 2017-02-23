##[Leetcode 235] Lowest Common Ancestor of BST

##题目
>BST中的两个节点的最低公共祖先


##思路
>递归
>根据BST的性质，若root的值恰好在p,q中间或者与p,q相等，那么root就是最低公共祖先

##程序

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if((root.val - p.val)*(root.val - q.val) <= 0) return root;
        else if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        else return lowestCommonAncestor(root.right, p, q);
    }

##[Leetcode 236] Lowest Common Ancestor of Binary Tree

##题目
>二叉树中的两个节点的最低公共祖先

##思路
>1. **DFS**计算所有节点的前驱，存在哈希表中；再依次按顺序找出所有的前驱链表；最后比较p,q生成的两个链表的最后一个公共节点。这是我的想法，写起来比较复杂
>2.  也可以和235类似的方法。

##程序

程序1

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> pi = new HashMap<TreeNode, TreeNode>();
        pi.put(root, root);
        helper(pi, root);
        

        
        
        LinkedList<TreeNode> plist = new LinkedList<>();
        TreeNode tmp = p;
        while(tmp != root) {
            plist.add(tmp);
            tmp = pi.get(tmp);
        }
        plist.add(root);
        
        LinkedList<TreeNode> qlist = new LinkedList<>();
        tmp = q;
        while(tmp != root) {
            qlist.add(tmp);
            tmp = pi.get(tmp);
        }
        qlist.add(root);
        

        
        
        int p1 = plist.size() - 1;
        int p2 = qlist.size() - 1;


        
        while(p1 >= 0 && p2 >= 0 && plist.get(p1) == qlist.get(p2)) {
            p1--;
            p2--;
        }
        

        return plist.get(p1+1);
        

    }
    
    public void helper(HashMap<TreeNode, TreeNode> pi, TreeNode root) {
        if(root == null) return;
        if(root.left != null && pi.get(root.left) == null) pi.put(root.left, root);
        if(root.right != null && pi.get(root.right) == null) pi.put(root.right, root);
        helper(pi, root.left);
        helper(pi, root.right);
    }



程序2

     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right != null) return root;
        return left == null? right : left;

    }

> Written with [StackEdit](https://stackedit.io/).