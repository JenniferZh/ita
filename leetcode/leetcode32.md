##[leetcode 32]Longest Valid Parentheses
##题目

>Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

>For "(()", the longest valid parentheses substring is "()", which has length = 2.

>Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

##思路
 >**动态规划**
 >状态转移函数：以$dp_i$表示以第$i$个字母结尾的最大合法括号序列的长度，则有
 >**case 1**: 当str[i]为‘（’时，$dp_i=0$，因为没有以前括号结尾的的合法括号，故长度为零。
 >**case 2**: 当str[i]为‘）’时，
 >1. 首先，他不能是一个突兀的'）'而没有任何前括号和他匹配，也就是向前跳过以他的前一个为结尾的最长匹配个数之后的位置是不是有一个‘（’和他匹配（如果没有,$dp_i=0$），这个判定条件如下：
 >if(i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(')    
>2. 其次，如果可以匹配，那么$dp_i$的大小就是：**这个后括号和与他匹配的前括号中间包着的所有部分（包括他们自己）的长度** 加上 **这一坨前面的最长匹配长度**，即dp[i] = dp[i-1]+2 + dp[i-dp[i-1]-2];

>举个例子
>| ）|（|）|（|（|）|）|
| -------- |--------|--------|
| 0 | 0 | 2 | 0 | 0 | 2 | 6 |

##code

    public class Solution {
	    public int longestValidParentheses(String s) {
	        int len = s.length();
	        int curmax = 0;
        
	        if(len <= 1) return 0;
        
	        int[] dp = new int[len];
        
	        for(int i = 1; i < len; i++) {
	            if(s.charAt(i) == ')') {
                
	                if(i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(') 
	                    dp[i] = dp[i-1]+2 + ((i-dp[i-1]-2 >= 0)?dp[i-dp[i-1]-2]:0);
	            }
	            curmax = Math.max(curmax, dp[i]);
	        }

	        return curmax;
        
	    }
	}


> Written with [StackEdit](https://stackedit.io/).