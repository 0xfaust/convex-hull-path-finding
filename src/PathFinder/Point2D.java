// Point2D.java
// C.McArdle, DCU, 2016

package pathFinder;

import edu.princeton.cs.introcs.StdDraw;

public class Point2D implements Comparable<Point2D>{

	private final double x;
	private final double y;

	public Point2D(double x, double y) { // constructor
		this.x = x;
		this.y = y;
	}

	public Point2D(Point2D p) { // copy constructor
		if (p == null)  System.out.println("Point2D(): null point!");
		x = p.getX();
		y = p.getY();
	}
	
	public double getX() { return x; }

	public double getY() { return y; }

	//calculate turning direction, similar to polar angle 
	public static double turningDirection(Point2D p0, Point2D p1, Point2D p2) {
		double a1 = (p1.getX()-p0.getX());
		double a2 = (p2.getX()-p0.getX());
		double b1 = (p1.getY()-p0.getY());
		double b2 = (p2.getY()-p0.getY());
		double z = (a1*b2)-(b1*a2);
		if(z < 0) return 1; //right
		else if(z > 0) return -1; //left
		else //colinear
			return 0;
	}
	public int compareTo(Point2D p2) { //compare two points
	      Point2D p1 = this;
	      if(p1.getX()+p1.getY()>p2.getX()+p2.getY())
	    	  return -1;
	      else if(p1.getX()+p1.getY()<p2.getX()+p2.getY())
	    	  return 1;
	      else
	    	  return 0;
	}
	
	@Override
	public String toString() {
		return ("(" + x + "," + y + ")\n");
	}

	public void draw() {
		StdDraw.point(x, y);
	}

}
