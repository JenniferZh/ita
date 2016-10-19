package test;

import java.util.Random;

public class RadixSort {
	public void printA(long[] A) {
		for(long i: A) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public void RandomInput(int num, long[] A) {
		Random rand = new Random();
		for(int i = 0; i < num; i++)
			A[i] = rand.nextLong() & 0xffffffffL;
	}
	
	public void RadixSort(long arr[], int r) {
		//rightmovebit
		int c = 0;
		int pass = (int) Math.ceil(32.0/r);
		int len = arr.length;
		
		int cntlen = 2;
		for(int i = 1; i < r; i++) {
			cntlen = cntlen << 1;
		}
		int mask = cntlen - 1;
		
		//System.out.println(filter+" "+cntlen);
		
		int[] cnt = new int[cntlen];
		long[] tmp = new long[len];
		
		for(int i = 0; i < pass; i++) {
			for(int j = 0; j < cntlen; j++)
				cnt[j] = 0;
			for(int j = 0; j < len; j++) {
				int pos = (int) ((arr[j] >> c) & mask);
				cnt[pos]++;
			}
			for(int j = 1; j < cntlen; j++)
				cnt[j] = cnt[j] + cnt[j-1];
			for(int j = len-1; j > -1; j--) {
				int pos = (int) ((arr[j] >> c) & mask);
				tmp[cnt[pos] - 1] = arr[j];
	            cnt[pos]--;
			}
			for(int j = 0; j < len; j++)
				arr[j] = tmp[j];
			c = c + r;
		}
		
	}

	public void SpeedTest(int num) {
		long[] A = new long[num];
		RandomInput(num, A);
		int times = 10;
		long starttime = 0, endtime = 0, duration = 0;
		long[] B = null;
		
		for(int k = 2; k < 13; k++) {
		duration = 0;
		for(int j = 0; j < times; j++) {
			B = new long[A.length];
			for(int i = 0; i < A.length; i++)
				B[i] = A[i];
			
		
			starttime = System.currentTimeMillis();
			RadixSort(B, k);
			
			endtime = System.currentTimeMillis();
			duration += endtime - starttime;
		}
		System.out.println("quicksort "+duration+" k "+k);
		}
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new RadixSort().SpeedTest(10000000);
		

	}

}
