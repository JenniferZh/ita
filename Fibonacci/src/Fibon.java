
public class Fibon {
	
	public long recur(long n) {
		if(n <= 2) return 1;
		else return recur(n-1)+recur(n-2);
	}
	
	public long iterative(long n) {
		long a = 1, b = 1;
		for(long i = 3; i <= n; i++){
			long tmp = a + b;
			a = b;
			b = tmp;
		}
		return b;
	}
	
	public long matrix(long n) {
		long[][] base = new long[][]{{1,1}, {1,0}};
		long[][] cur = new long[][]{{1,1}, {1,0}};
		for(long i = 0; i < n-2; i++) {
			cur = multiply(cur, base);
		}
		return cur[0][0];
	}
	
	public long matrix2(long n) {
		if(n == 0) return 0;
		
		long[][] ans = power(n-1);
		return ans[0][0];
	}
	
	public long[][] power(long n) {
		long[][] cur = new long[][]{{1,1}, {1,0}};
		long[][] base = new long[][]{{1,1}, {1,0}};
		if(n == 0 || n == 1) return cur;
		long[][] ans = power(n/2);
		ans = multiply(ans, ans);
		if(n%2 != 0) ans = multiply(ans, base);
		return ans;
	}
	
	public long[][] multiply(long F[][], long M[][])
    {
		long x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
		long y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
		long z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
		long w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];
     
		F[0][0] = x;
		F[0][1] = y;
		F[1][0] = z;
		F[1][1] = w;
		
		return F;
    }
	
	public void test(long n) {
		long start, end;
		
		start = System.currentTimeMillis();
		iterative(n);
		end = System.currentTimeMillis();
		System.out.println("iterative "+ (end - start));
		
		start = System.currentTimeMillis();
		matrix(n);
		end = System.currentTimeMillis();
		System.out.println("matrix "+ (end - start));
		
		
		start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) 
			matrix2(n);
		end = System.currentTimeMillis();
		System.out.println("divide and conquer "+ ((double)end - start)/100);
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		Fibon a = new Fibon();
		for(int i = 1000; i <= 100000000; i = i*10)
			a.test(i);

	}

}
