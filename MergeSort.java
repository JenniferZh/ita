package test;

public class MergeSort {
	
	//add a sentinel to the last of array left and right
	public void Merge(int[] arr, int p, int q, int r) {

		
		int[] left = new int[q - p + 2];
		int[] right = new int[r - q + 1];
		for(int i = 0; i < q - p + 1; i++)
			left[i] = arr[p+i];
		for(int i = 0; i < r - q; i++)
			right[i] = arr[q+i+1];
		
		
		left[q-p+1] = Integer.MAX_VALUE;
		right[r-q] = Integer.MAX_VALUE;
		int x = 0, y = 0;
		for(int i = p; i <= r; i++) {
			if(left[x] <= right[y]) {arr[i] = left[x]; x++;}
			else {arr[i] = right[y]; y++;}
		}
	}
	
	public void Mergesort(int[] arr, int p, int r) {
		if(p < r) {
			int q = (p+r)/2;
			
			Mergesort(arr, p, q);
			Mergesort(arr, q+1, r);
			Merge(arr, p, q, r);
		}
	}
	
	//sub array: [p, q)  and [q, r) merge not using sentinel
	public void Merge2(int[] arr, int p, int q, int r) {
		int[] tmp = new int[r-p];
		int p1 = p, p2 = q, p3 = 0;
		while(p3 < r-p) {
			if(p1 < q && (p2 >= r || arr[p1] < arr[p2])) 
				tmp[p3++] = arr[p1++];
			else tmp[p3++] = arr[p2++];
		}
		p3 = 0;
		for(int i = p; i < r; i++)
			arr[i] = tmp[p3++];
	}
	
	//arr [p, r)
	public void Mergesort2(int[] arr, int p, int r) {
		if(r-p < 2) return;
			int q = (p+r)/2;
			System.out.println(p+" "+q+" "+r);
			Mergesort2(arr, p, q);
			Mergesort2(arr, q, r);
			Merge2(arr, p, q, r);
		
	}
	
	public static void main(String[] args) {
		MergeSort a = new MergeSort();
		int[] t = new int[]{5,2,4,7,1,3,2,6};
		a.Mergesort2(t, 0, t.length);
		for(int tmp: t) System.out.print(tmp+" ");
		System.out.println();
	}

}
