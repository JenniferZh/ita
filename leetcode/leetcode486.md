##[Leetcode 486] Predict the Winner

##题目

>Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

>Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

>Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.


#思路

>**动态规划**, **minimax**
>1. 用$dp[i,j]$代表数组下标从$i$到$j$第一个游戏者可以拿到的最高分，也就是如果他取第$i$个数字拿到的分数和取第$j$个数字拿到的分数较大的那个。
>2. 取第$i$个数字拿到的分数，是若第二个游戏者拿了第$i+1$或拿了$j-1$个数字中**更小**的那个
>3. 取第$j$个数字拿到的分数，是若第二个游戏者拿了第$i$或拿了$j-2$个数字中**更小**的那个


##程序

    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for(int num: nums) sum += num;
        
        int k = helper(nums, 0, nums.length-1);
        if(k >= sum-k) return true;
        else return false;
    }
    
    
    int helper(int[] nums, int start, int end) {
        if(end == start) return nums[start];
        if(end - start == 1) return Math.max(nums[start], nums[end]);
        int s1 = helper(nums, start+2, end);
        int s2 = helper(nums, start+1, end-1);
        
        int s3 = helper(nums, start+1, end-1);
        int s4 = helper(nums, start, end-2);
        
        
        int p2 = Math.min(s3, s4)+nums[end];
        int p1 = Math.min(s1, s2)+nums[start];
        
        return Math.max(p1, p2);
    }

> Written with [StackEdit](https://stackedit.io/).