package test;

import java.util.HashSet;
import java.util.Random;

public class SortingAlgorithm {
	//long[] A = {3,5,6,2,67,23,56,34};
	
	public void QuickSort(int start, int end, long[] A) {
		if(start < end) {
			int p = Partition(start, end, A);
			QuickSort(start, p-1, A);
			QuickSort(p+1, end, A);
		}
	}
	
	public int Partition(int start, int end, long[] A) {
		long pivot = A[end];
		int i = start - 1;
		for(int j = start; j < end; j++) {
			if(A[j] <= pivot) {
				i++;
				Exchange(i, j, A);
			}
		}
		Exchange(i+1, end, A);
		return i+1;
	}
	
	public void Exchange(int i, int j, long[] A) {
		long tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
	
	public void MergeSort(int start, int end, long[] A) {
		if(start < end) {
		int mid = start + (end - start)/2;
		MergeSort(start, mid, A);
		MergeSort(mid+1, end, A);
		Merge(start, mid, end, A);
		}
	}
	
	public void Merge(int start, int mid, int end, long[] A){

		long[] tmp = new long[end-start+1];
		int k = 0;
		for(int i = start, j = mid+1; k != (end-start+1); ) {
			if(i <= mid && (j > end || A[i] < A[j])) 
				tmp[k++] = A[i++];
			else tmp[k++] = A[j++];
		}
		k = 0;
		for(int i = start; i <= end; i++) 
			A[i] = tmp[k++];
	}
	
	public void InsertionSort(int start, int end, long[] A) { 
		for(int i = start+1; i <= end; i++) {
			long tmp = A[i];
			int j = i-1;
			for(j = i-1; j >= start; j--) {
				if(A[j] > tmp) A[j+1]=A[j];
				else break;
			}
			A[j+1] = tmp;
		}
	}
	
	public void ShellSort(int len, long[] A) {
		int gap, i, j;
		long key;
		for(gap = len/2; gap > 0; gap = gap/2) {
			for(i = gap; i < len; i++) {
				key = A[i];
				for(j = i - gap; j >= 0 && A[j] > key; j = j - gap)
					A[j + gap] = A[j];
				A[j + gap] = key;
			}
		}
	}

	public void RadixSort(int len, long[] A) {
		int[] count = new int[10];
		long[] tmp = new long[len];
		long radix = 1;
		long pos;
		for(int i = 1; i < 11; i++) {
			for(int j = 0; j < 10; j++) 
				count[j] = 0;
			for(int j = 0; j < len; j++) {
				pos = (A[j]/radix)%10;
				
				count[(int) pos]++;
			}
			for(int j = 1; j < 10; j++) {
				count[j] = count[j-1]+count[j];
			}
			for(int j = len-1; j > -1; j--) {
				pos = (A[j]/radix)%10;
				tmp[count[(int)pos]-1] = A[j];
				count[(int) pos]--;
			}
			for(int j = 0; j < len; j++)
				A[j] = tmp[j];
			radix = radix*10;
			
		}
	}
	
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
	
	public void Speedtest(int num) {
		long[] A = new long[num];
		RandomInput(num, A);
		int times = 1000;
		long starttime = 0, endtime = 0, duration = 0;
		long[] B = null;
		
		for(int j = 0; j < times; j++) {
			B = new long[A.length];
			for(int i = 0; i < A.length; i++)
				B[i] = A[i];
			//printA(B);
		
			starttime = System.currentTimeMillis();
			QuickSort(0, num-1, B);
			endtime = System.currentTimeMillis();
			duration += endtime - starttime;
		}
		System.out.println("quicksort"+duration);
		//printA(B);
		
		
		duration = 0;
		for(int j = 0; j < times; j++) {
		B = new long[A.length];
		for(int i = 0; i < A.length; i++)
			B[i] = A[i];
		//printA(B);
		starttime = System.currentTimeMillis();
		MergeSort(0, num-1, B);
		endtime = System.currentTimeMillis();
		duration += endtime - starttime;
		}
		System.out.println("mergesort"+duration);
		//printA(B);
		
		duration = 0;
		for(int j = 0; j < times; j++) {
		B = new long[A.length];
		for(int i = 0; i < A.length; i++)
			B[i] = A[i];
		//printA(B);
		starttime = System.currentTimeMillis();
		RadixSort(num, B);
		endtime = System.currentTimeMillis();
		duration += endtime - starttime;
		}
		System.out.println("radixsort"+duration);
		//printA(B);
		
		duration = 0;
		for(int j = 0; j < times; j++) {
		B = new long[A.length];
		for(int i = 0; i < A.length; i++)
			B[i] = A[i];
		//printA(B);
		starttime = System.currentTimeMillis();
		ShellSort(num, B);
		endtime = System.currentTimeMillis();
		duration += endtime - starttime;
		}
		System.out.println("shellsort"+duration);
		//printA(B);
		
		duration = 0;
		for(int j = 0; j < times; j++) {
		B = new long[A.length];
		for(int i = 0; i < A.length; i++)
			B[i] = A[i];
		//printA(B);
		starttime = System.currentTimeMillis();
		InsertionSort(0, num-1, B);
		endtime = System.currentTimeMillis();
		duration += endtime - starttime;
		}
		System.out.println("insertionsort"+duration);
		//printA(B);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortingAlgorithm a = new SortingAlgorithm();
		//a.RadixSort(a.A.length);
		//a.printA();
		a.Speedtest(1000);
		System.out.println("ja");
		//a.randomtest();
		

	}

}
