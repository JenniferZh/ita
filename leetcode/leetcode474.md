##[Leetcode 474] Ones and Zeroes

##题目

>In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

>For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

>Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

>Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.

>Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

>Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
>
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

>Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

##思路

>**动态规划-01背包**
>注意这种问题本质就是某个元素要不要，这种01背包问题有固定的写法，即循环最外层是依次遍历每个元素代表选择还是不选择，内部使用正常的dp递推表达式，注意是从后往前遍历。
>类似的问题：子集和问题
>所以没想出来不应该呀！

##程序

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for(String s: strs) {
            int numzero = 0;
            int numone = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '0') numzero++;
                else numone++;
            }
            
            for(int i = m; i >= numzero; i--)
                for(int j = n; j >= numone; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-numzero][j-numone] + 1);
                }
                
            
            
        }
        
        return dp[m][n];
        
    }
    


> Written with [StackEdit](https://stackedit.io/).