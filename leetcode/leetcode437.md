
##[Leetcode 437] Path Sum III

##题目

>一个树中有几条路径的和(这个路径可以从任意节点开始任意节点结束，只要是自下而上的)等于sum

##思路

>1。 **DFS**
>计算由某个节点开始有几条路径，再遍历整棵树对每个节点调用这个函数
>2。 用哈希记录下每个sum下已有的路径数来加速


##程序

程序1

     public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return helper(root, sum)+pathSum(root.left, sum)+pathSum(root.right, sum);
    }
    

    
    public int helper(TreeNode root, int sum) {
        if(root == null) return 0;
        int a = helper(root.left, sum-root.val);
        int b = helper(root.right, sum-root.val);
        if(root.val == sum) return a+b+1;
        else return a+b;
    }


程序2

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

> Written with [StackEdit](https://stackedit.io/).