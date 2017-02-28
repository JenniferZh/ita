
##[Leetcode 508] Most Frequent Subtree Sum

##题目

>出现次数最多的子树和
>Examples 1
>Input:

  > 5
 / \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  >5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.


##思路

>普通递归加哈希存储
>注意：哈希时一个常用函数
>int count = sumToCount.getOrDefault(sum, 0) + 1;
        sumToCount.put(sum, count);

##程序

    public int[] findFrequentTreeSum(TreeNode root) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        helper(root, map);
        int max = Integer.MIN_VALUE;
        int cnt = 0;
        for(Integer p: map.keySet()) {
            if(map.get(p) > max) max = map.get(p);
        }
        for(Integer p: map.keySet()) {
            if(map.get(p) == max) cnt++;
        }
        int[] result = new int[cnt];
        int i = 0;
        for(Integer p: map.keySet()) {
            if(map.get(p) == max) result[i++] = p;
        }
        return result;
    }
    
    public int helper(TreeNode root, HashMap<Integer, Integer> map) {
        if(root == null) return 0;
        int subsum = root.val + helper(root.left, map) + helper(root.right, map);
        if(map.get(subsum) == null) map.put(subsum, 1);
        else map.put(subsum, 1+ map.get(subsum));
        return subsum;
    }

> Written with [StackEdit](https://stackedit.io/).