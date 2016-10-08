import java.util.Comparator;

class Point implements Comparable<Point>{
	double x;
	double y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		if(this.x > o.x) return 1;
		else if(this.x == o.x) return 0;
		else return -1;
	}
	
	//sort the points according to the Y-axis
	public static Comparator<Point> YComparator = new Comparator<Point>() {
		public int compare(Point a, Point b) {
			if(a.y > b.y) return 1;
			else if(a.y == b.y) return 0;
			else return -1;
		}
	};
	public String toString() {
		return "["+x+" ,"+y+"]";
	}
}