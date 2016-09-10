package test;

public class InsertionSort {
	
	//increasing order
	public int[] Insertionsort(int[] arr) {
		if(arr == null || arr.length == 1) return arr;
		for(int j = 1; j < arr.length; j++) {
			int key = arr[j], i = j-1;
			while(i > -1 && arr[i] > key) {
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key;
		}
		return arr;
	}
	
	//decreasing order
	public int[] Insertionsort2(int[] arr) {
		if(arr == null || arr.length == 1) return arr;
		for(int j = 1; j < arr.length; j++) {
			int key = arr[j], i = j-1;
			while(i > -1 && arr[i] < key) {
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key;
		}
		return arr;
	}
	
	//ex:2.3-4 recursive way to sort [start, end) 
	public void recurinsertionsort(int[] arr, int start, int end) {
		if(end - start < 2) return;
		recurinsertionsort(arr, start, end - 1);
		int key = arr[end-1], tmp = end-2;
		while(tmp > start-1 && arr[tmp] > key) {
			arr[tmp+1] = arr[tmp];
			tmp--;
		}
		arr[tmp+1] = key;
	}
	
	public static void main(String[] args) {
		InsertionSort a = new InsertionSort();
		int[] tmp = new int[]{5,2,4,6,1,3};
		a.recurinsertionsort(tmp, 0, 6);
		for(int t: tmp) System.out.print(t+" ");
		System.out.println();
	}

}
