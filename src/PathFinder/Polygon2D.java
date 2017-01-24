// Polygon2D.java

package pathFinder;

import java.awt.Color;
import java.util.Arrays;
import edu.princeton.cs.introcs.StdDraw;

public class Polygon2D {

	private Point2D[] vertices;
	
	public Polygon2D() {  // default constructor
		vertices = new Point2D[0];
	}

	public Polygon2D(Point2D[] points) {  // constructor
		vertices = new Point2D[points.length];
		for (int i=0; i<points.length; i++) {
			vertices[i] = new Point2D(points[i]);
		}
	}

	public Polygon2D(Polygon2D poly) {  // copy constructor
		vertices = new Point2D[poly.vertices.length];
		for (int i=0; i<poly.vertices.length; i++) {
			vertices[i] = new Point2D(poly.vertices[i]);
		}
	}

	public void addPoint(Point2D p) {
		vertices = Arrays.copyOf(vertices, vertices.length + 1);
		vertices[vertices.length-1] = new Point2D(p);
	}

	public int size() {
		return vertices.length;
	}
	
	public Point2D[] asPointsArray() {
		return Arrays.copyOf(vertices, vertices.length);
	}
	
	private Point2D lowest;
	
	public Point2D getLowest() { return lowest; }
	public void setLowest(Point2D lowest) { this.lowest=lowest; }
	
	public void lowestPoint(Point2D[] points) {
		Point2D lowest = points[0];
		for(int i=0; i<points.length-1;i++){
			if(points[i].getY()<=lowest.getY()) {
				if(points[i].getY()==lowest.getY() && points[i].getX()<lowest.getX()) {
					lowest = points[i];
				}
				else if(points[i].getY()!=lowest.getY()){
					lowest = points[i];
				}
			}
		}
		setLowest(lowest);	
	}
	
	public void draw() {
		for (int i=0; i<vertices.length; i++) {
			if (i < vertices.length-1)
				StdDraw.line(vertices[i].getX(), vertices[i].getY(), vertices[i+1].getX(), vertices[i+1].getY());
			else
				StdDraw.line(vertices[i].getX(), vertices[i].getY(), vertices[0].getX(), vertices[0].getY());
		}
	}

	public void drawFilled() {
		double[] X = new double[vertices.length];
		double[] Y = new double[vertices.length];
		for (int i=0; i<vertices.length; i++) {
			X[i] = vertices[i].getX();
			Y[i] = vertices[i].getY();
		}
		StdDraw.filledPolygon(X, Y);
	}
	
	@Override
	public String toString() {
		String polyAsString = "";
		for (int i=0; i<vertices.length; i++)
			polyAsString += vertices[i].toString() + " "; 
		return polyAsString;
	}
	
	
	// TEST CLIENT //
	public static void main(String args[]) {
		Point2D[] shape = new Point2D[4];
		shape[0] = new Point2D(0.2,0.2);
		shape[1] = new Point2D(0.2,0.8);
		shape[2] = new Point2D(0.8,0.8);
		shape[3] = new Point2D(0.8,0.2);
		Polygon2D pg = new Polygon2D(shape);
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(Color.RED);
		pg.draw();
	}
}
