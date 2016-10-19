package test;

import java.util.ArrayList;
import java.util.List;

public class RodcutAlgorithm {
	
	
	//top-down without memorizing
	public int CutRod(int[] p, int len) {
		if(len == 0) return 0;
		int max = Integer.MIN_VALUE;
		for(int i = 1; i < p.length; i++) {
			if(len-i >=0) {
				int tmp = CutRod(p, len - i) + p[i];
				if(tmp > max) max = tmp;
			}
		}
		return max;
	}
	
	//top-down with memorizing
	public int MemCutRod(int[] p, int len) {
		int[] r = new int[len+1];
		int[] s = new int[len+1];
		for(int i = 1; i < len+1; i++) {
			r[i] = -1;
		}
		
		helper(p,r,s,len);
		List<Integer> res = Find(len, s);
		for(Integer ss: res) System.out.print(ss + " ");
		System.out.println();
		return r[len];
	}
	public int helper(int[] p, int[] r, int [] s, int len) {

		if(len == 0) return 0;
		if(r[len] > 0) return r[len];
		int max = Integer.MIN_VALUE;
		int pos = 0;
		for(int i = 1; i < p.length; i++) {
			if(len-i>=0){
				int tmp = helper(p,r,s,len-i);
				if(tmp+p[i] > max) {
					max = tmp+p[i];
					pos = i;
				}
			}
		}
		r[len] = max;
		s[len] = pos;
		return max;
	}
	public List<Integer> Find(int len, int[] s) {
		List<Integer> res = new ArrayList<Integer>();
		while(s[len] != 0) {
			res.add(s[len]);
			len = len - s[len];
		}
		return res;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] p = {0,1,5,8,9,10,17,17,20,24};
		RodcutAlgorithm a = new RodcutAlgorithm();
		//System.out.println(a.CutRod(p, 30));
		System.out.println(a.MemCutRod(p, 30));

	}

}
