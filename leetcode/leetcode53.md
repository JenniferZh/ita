
#[Leetcode 53]Maximum Subarray
##题目
>Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

>For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
>the contiguous subarray [4,-1,2,1] has the largest sum = 6.

##思路
>**动态规划**
>状态转移函数：$s_i$表示以第$i$个数字结尾的subarray的最大sum。
>$s_i=max(s_{i-1}+nums[i], nums[i])$

##code
    public class Solution {
	    public int maxSubArray(int[] nums) {
	        int len = nums.length;
	        int dp[] = new int[len];
	        int largestsum = dp[0] = nums[0];
        
	        for(int i = 1; i < len; i++) {
	            if(dp[i-1] > 0) dp[i] = nums[i] + dp[i-1];
	            else dp[i] = nums[i];
            
	            if(dp[i] > largestsum) largestsum = dp[i];
	        }        
	        return largestsum;
	    }
	 }

> Written with [StackEdit](https://stackedit.io/).
