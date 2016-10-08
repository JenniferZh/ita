class Pair{
	Point a;
	Point b;
	public Pair(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	public String toString() {
		return a+" "+b;
	}
	public double distance() {
		if(a == null || b == null) return Double.MAX_VALUE;
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}

	public static Pair findmin(Pair x, Pair y) {
		// TODO Auto-generated method stub
		if(x.distance() > y.distance()) return y;
		else return x;
	}
}