import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ClosestPointsAlgorithm {
	ArrayList<Point> pointSet = new ArrayList<Point>();

	
	public void generate(int num) {
		pointSet = new ArrayList<Point>();
		Random position = new Random();
		Point test;
		do {
			test = new Point(position.nextInt(500), position.nextInt(500));
			pointSet.add(test);
		} while (pointSet.size() < num);
	}

	
	public double twopointdistance(Point a, Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	public Pair ClosestStripPair(ArrayList<Point> strip) {
		Collections.sort(strip, Point.YComparator);
		double lrmin = Double.MAX_VALUE;
		Pair ans = null;
		for(int i = 0; i < strip.size(); i++) {
			for(int j = i+1; j < strip.size() && strip.get(j).y - strip.get(i).y < lrmin; j++) {
				if(twopointdistance(strip.get(i), strip.get(j)) < lrmin) {
					ans = new Pair(strip.get(i), strip.get(j));
					lrmin = twopointdistance(strip.get(i), strip.get(j));
				}
			}
		}
		return ans;
	}
	
	public Pair ClosetPair(int start, int end) {
		//if only one point, can't find a Pair, return null;
		if(end - start == 1) return new Pair(null, null);
		//if only two point, just return this two point;
		if(end - start == 2) return new Pair(pointSet.get(start), pointSet.get(start+1));
		
		//using mid to partition the list into two part, and recursively compute the closet pair of two part
		int mid = start + (end - start)/2;
		Pair left = ClosetPair(start, mid);
		Pair right = ClosetPair(mid, end);
		
		//System.out.println("left "+left+" right "+right);
		//store the min distance of two part into mindis
		double mindis = Math.min(left.distance(), right.distance());
		Pair minpair = Pair.findmin(left, right);
		
		//compute the lrclosestpair between to part
		ArrayList<Point> strip = new ArrayList<Point>();
		for(int i = mid-1; i > start-1 && Math.abs(pointSet.get(i).x - pointSet.get(mid).x) < mindis; i--) {
			strip.add(pointSet.get(i));
		}
		for(int i = mid; i < end && Math.abs(pointSet.get(i).x - pointSet.get(mid).x) < mindis; i++) {
			strip.add(pointSet.get(i));
		}
		//System.out.println("strip: "+strip);
		
		Pair lrpair = ClosestStripPair(strip);
		if(lrpair == null || lrpair.distance() > mindis) return minpair;
		else return lrpair;
		
	}
	
	public Pair stupidmethod() {
		double a = Double.MAX_VALUE;
		Pair ans = null;
		for(int i = 0; i < pointSet.size(); i++) {
			for(int j = i+1; j < pointSet.size(); j++) {
				if(twopointdistance(pointSet.get(i),pointSet.get(j)) < a) {
					a = twopointdistance(pointSet.get(i),pointSet.get(j));
					ans = new Pair(pointSet.get(i), pointSet.get(j));
				}
			}
		}
		return ans;
	}
	
	public void testgenerate(int n, int range) {
		Random ran = new Random();
		pointSet = new ArrayList<Point>();
		for(int i = 0; i < n; i++)
			pointSet.add(new Point(ran.nextDouble()*range,ran.nextDouble()*range));
	}
	

	
	public void MethodsCompare(int pointsnum) {
		testgenerate(pointsnum, 10000);
		long time1 = evaluatetime(pointsnum, 0);
		long time2 = evaluatetime(pointsnum, 1);
		System.out.println(time1 + " " + time2);
	}
	
	public long evaluatetime(int pointsnum, int choice) {
		long start, end, timesum = 0;
		for(int i = 0; i < 10; i++) {
			start = System.currentTimeMillis();
			if(choice == 0)
				stupidmethod();
			else {
				Collections.sort(pointSet);
				ClosetPair(0, pointSet.size());
			}
			end = System.currentTimeMillis();
			timesum += (end - start);
		}
		return timesum/10;
	}
	

	
	
	
	public static void main(String[] args) {
		ClosestPointsAlgorithm m = new ClosestPointsAlgorithm();
		m.MethodsCompare(100000);
		
	}

}
