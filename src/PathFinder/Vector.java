package pathFinder;

import java.util.ArrayList;
import java.util.List;

public class Vector implements Comparable<Vector>{ //2D vector
 private final Point2D p0;
 private final Point2D p1;
 
 //constructor
 public Vector(Point2D p0, Point2D p1) {
  this.p0 = p0;
  this.p1 = p1;
 }
 //getters
 public Point2D getPoint() { return p1; }

 public Point2D getP0() { return p0; }
 public Point2D getP1() { return p1; }

 //method to remove collinear points
 public static Point2D[] removeColinear(Vector[] v){
  List<Point2D> hullPoints = new ArrayList<Point2D>(); //create hull points
  for (int i=0; i<v.length; i++){
   double area = v[i].calcArea(v[(i+1)%(v.length)]); //calculate area under 3 points
   if(area < 0.0001){ //if less than threshold 
   }
   else{
    hullPoints.add(v[i].getP1()); //add to hull points
   }
  }
  //convert to Point2D array
  Point2D[] hull = new Point2D[hullPoints.size()];
  hull = hullPoints.toArray(hull);
  return hull;
 }
 
 //method to calculate area for removing collinear points
 public double calcArea(Vector B){
  Vector A = this;
  return A.p0.getX() * (A.p1.getY() - B.p1.getY()) + A.p1.getX() * (B.p1.getY() - A.p0.getY()) + B.p1.getX() * (A.p0.getY() - A.p1.getY());
  
 }
 
 //compareto method
 public int compareTo(Vector B){
  
  Vector A = this;
  if(A == B) return 0;
  double a1 = (A.p1.getX()-A.p0.getX());
  double a2 = (B.p1.getX()-A.p0.getX());
  double b1 = (A.p1.getY()-B.p0.getY());
  double b2 = (B.p1.getY()-B.p0.getY());
  double cross = (a1*b2)-(b1*a2); //cross product
  
  if(cross == 0) return 0; //collinear
  else if(cross > 0) return 1; 
  else if(cross < 0) return -1;
  return 0;
 }
 
 @Override
 public String toString() {
  return ("\nEdge Point 1: (" + p0.getX() + "," + p0.getY() + ")" + "\tEdge Point 2: (" + p1.getX() + "," + p1.getY() + ")");
 }
 
 public static void main(String args[]){
 }
 
}