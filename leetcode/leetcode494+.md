##[Leetcode 416] Partition Equal Subset Sum

##题目
>Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

>Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

>Input: [1, 5, 11, 5]

>Output: true

>Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

>Input: [1, 2, 3, 5]

>Output: false

>Explanation: The array cannot be partitioned into equal sum subsets.

##思路
>求数组中有没有子集和为数组所有元素的和的一半。即为**子集和问题**。
>子集和问题是动态规划问题，若用$dp[i]$表示是否存在一个和为$i$的子集。则有递推表达式$$dp[i] = dp[i-nums_0] || dp[i-nums_1]||...||dp[i-nums_n],其中dp[0] = true$$
>递归时间会很久，迭代的写法非常巧妙。

##程序

    public class Solution {
	    public boolean canPartition(int[] nums) {
	        int sum = 0;
	        for(int i: nums) sum += i;
		    if(sum % 2 != 0) return false;
        
	        boolean dp[] = new boolean[sum/2+1];
	        //空集的和为零，因此为true
	        dp[0] = true;
	        //对于每一个数字
	        for(int i = 0; i < nums.length; i++) {
	        
		        //对每一个大于等于当前选择的数字，小于等于目标和的数字进行反向遍历，每一轮结束之后，dp数组中为true的会是前一次已经生成的合法子集和分别加上当前选择的数字。反向遍历的原因是，正向遍历会让新生成的子集和重复被使用产生错误
	            for(int j = sum/2; j >= nums[i]; j--)
	            
		            //如果当前选择的数字已经是子集和了，就不再考虑dp[j-nums[i]]
	                dp[j] = dp[j] || dp[j-nums[i]];
	        }
        
	        return dp[sum/2];
	    }    
	}

##[Leetcode 494] Target Sum

##题目
>You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

>Find out how many ways to assign symbols to make sum of integers equal to target S.

>Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

>-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

>There are 5 ways to assign symbols to make the sum of nums be target 3.
>Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.

##思路

>1. **动态规划**的递推式，假设dp[s, i] 为数组中以下标 $i$ 结尾的子数组可以达到 $s$ 计算结果的运算式的个数，$dp[s,i] = dp[s+nums_i, i-1]+dp[s-nums_i, i-1]$ ,特别的，当i=0分为三种情况，详见程序1

>2. 转化为上面的**子集和问题**。
>找到需要赋予正号的子集和需要赋予负号的子集。

Let P be the positive subset and N be the negative subset
For example:
Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

Then let's see how this can be converted to a subset sum problem:

                  sum(P) - sum(N) = target
                  sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                  2 * sum(P) = target + sum(nums)
                  sum(P) = (target + sum(nums)) / 2

##程序

程序1

    public class Solution {
	    public int findTargetSumWays(int[] nums, int S) {
	        return helper(nums, nums.length-1, S);
	    }
    
	    public int helper(int[] nums, int i, int S) {
	        if(i == 0) {
	            if(nums[i] == 0 && S == 0) return 2;
	            if(S == nums[i] || S == -nums[i]) return 1;
		            else return 0;
	        } else {
	            return helper(nums, i-1, S+nums[i])+helper(nums, i-1, S-nums[i]);
	        }
	    }
	}

程序2

    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
    }   

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    } 


##[Leetcode 377] Combination Sum IV

##题目
>Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

>Example:

>nums = [1, 2, 3]
target = 4

>The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

>Note that different sequences are counted as different combinations.

>Therefore the output is 7.


##思路

>由于题目是计入顺序的，所以用过的数字可以再用，因此外层循环是dp[i]，对于每个dp[i]都做nums的循环，见程序1。
>想想如果是不计入顺序的，以上结果应该是4，外层循环为nums会保证每个结果必然按照在nums中的排列顺序，即只会有一种顺序，见程序2


##程序

程序1

    public class Solution {
	    public int combinationSum4(int[] nums, int target) {
	        int[] dp = new int[target+1];
	        dp[0] = 1;
	        for(int i = 1; i <= target; i++) {
	            for(int num: nums) {
	                if(i-num >= 0)
	                    dp[i] += dp[i-num];
	            }
	        }
	        return dp[target];       
	    }
	}

程序2

     public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = num; i <= target; ++i) {
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }

> Written with [StackEdit](https://stackedit.io/).