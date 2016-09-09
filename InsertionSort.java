package test;

public class InsertionSort {
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
	
	public static void main(String[] args) {
		InsertionSort a = new InsertionSort();
		int[] tmp = a.Insertionsort(new int[]{5,2,4,6,1,3});
		for(int t: tmp) System.out.print(t+" ");
		System.out.println();
	}

}
