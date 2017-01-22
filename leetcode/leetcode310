
##[Leetcode 310]Minimum Height Trees

##题目

>For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

>Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

>You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

>Example 1:

>Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
>return [1]

##思路
>1。一棵树里的所有最长路径都有共同的中点
>
>证明：假设存在两条最长路径，他们的中点不同。
>如果这个最长路径是偶数m，并且这两条不同的最长路径一定有不同的起点或者不同的终点或者都不同。
>假设是不同的终点，那么从这两个不同的中点到各自的终点的路径长度问m/2，这两个不同的中点之间的距离至少是1，因此可以找到一条更长的最长路径 终点1--中点1--中点2--终点2，这条路径的距离至少为m+1，矛盾。因此假设不成立。
>最长路径是奇数，那么就取两条不同路径中较长的那一边，也可以找到更长的最长路径终点1--中点1--中点2--终点2。因此假设不成立。
>
>2。以一棵树里最长路径的中点为根节点，即可得到最小的高度
>3。一棵树的的最长路径算法：求树的直径（BFS）
	>i 以任意一个点为起点，做BFS找到离这个点最远的点u
	>ii 以点u为起点，再做一遍BFS找到离这个点最远的点v
	>iii u,v就是树的最长路径的起点和终点


##code

   public class Solution {
	
	class vertex {
		int id;
		int color;
		int d;
		int pi;
		public vertex(int id) {
			// TODO Auto-generated constructor stub
			this.id = id;
		}
		
		public void setinitial() {
			color = 0;
			d = 0;
			pi = -1;
		}
		
		public String toString() {
			return id+" "+d+" "+pi;
		}
	}
	
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	List<Integer> result = new ArrayList<>();
    	if(n == 0) return result;
    	
    	//vertex list
    	List<vertex> vList = new ArrayList<>();
    	for(int i = 0; i < n; i++) vList.add(new vertex(i));
    	
    	
    	//create adjacency list
    	List<List<vertex>> adj = new ArrayList<List<vertex>>();
    	for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
    	for(int i = 0; i < edges.length; i++) {
    		int p = edges[i][0];
    		int q = edges[i][1];
    		adj.get(p).add(vList.get(q));
    		adj.get(q).add(vList.get(p));
    	}
    	
    	
    	
    	
    	//first pass bfs
    	LinkedList<vertex> Q = new LinkedList<>();
    	Q.add(vList.get(0));
    	int maxd = 0;
    	while(!Q.isEmpty()) {
    		vertex u = Q.poll();
    		List<vertex> tmp = adj.get(u.id);
    		for(vertex v: tmp) {
    			if(v.color == 0) {
    				v.d = u.d + 1;
    				if(v.d > maxd) maxd = v.d;
    				Q.add(v);
    			}
    		}
    		u.color = 1;
    	}
    	
    	ArrayList<vertex> endpoint = new ArrayList<>();
    	//deepest deep in first pass
    	for(vertex t: vList) {
    		if(t.d == maxd)  endpoint.add(t);
    	}
    	
    	HashSet<Integer> res = new HashSet<>();
    	
    	
    	//for each deep pass
    	for(vertex end: endpoint) {
    		System.out.println(end.id);
    		
    		for(vertex t: vList) t.setinitial();
    		LinkedList<vertex> tmpQ = new LinkedList<>();
        	tmpQ.add(end);
        	int tmpmaxd = 0;
        	while(!tmpQ.isEmpty()) {
        		vertex u = tmpQ.poll();
        		List<vertex> tmp = adj.get(u.id);
        		for(vertex v: tmp) {
        			if(v.color == 0) {
        				v.d = u.d + 1;
        				v.pi = u.id;
        				if(v.d > tmpmaxd) tmpmaxd = v.d;
        				tmpQ.add(v);
        			}
        		}
        		u.color = 1;
        	}
        	
        	ArrayList<vertex> newendpoint = new ArrayList<>();
        	for(vertex t: vList) {
        		if(t.d == tmpmaxd)  newendpoint.add(t);
        	}
        	

        	
        	
        	
        	if(tmpmaxd % 2 == 0) {
        		for(vertex t: newendpoint) {
        			vertex cur = t;
        			for(int i = 0; i < tmpmaxd/2; i++) cur = vList.get(cur.pi);
        			res.add(cur.id);
        		}
        	} else {
        		for(vertex t: newendpoint) {
        			vertex cur = t;
        			for(int i = 0; i < tmpmaxd/2; i++) cur = vList.get(cur.pi);
        			res.add(cur.id);
        			res.add(cur.pi);
        		}
        	}
    	}
    	
    	result.addAll(res);

    	return result;
    }
}


> Written with [StackEdit](https://stackedit.io/).