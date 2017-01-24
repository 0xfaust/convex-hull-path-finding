package pathFinder;
import java.util.*;
public class Dijkstra {
	
   public static void main(String[] args) {
   }
}
 
class Graph { //graph class
   private final Map<Point2D, Vertex> graph; 
   
      public static class Edge { //edge class
      public final Point2D v1; //points at either end of the edge
      public final Point2D v2;
      public final double dist; //distance between them
      public Edge(Point2D v1, Point2D v2, double dist) { //edge constructor
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
      public String toString() { return ("(" + v1 + "," + v2 + ")\n");}
      public Point2D getp0(){ //getters for points
    	  return v1;
      }
      public Point2D getp1(){
    	  return v2;
      }

   }
 
  public static class Vertex implements Comparable<Vertex>{ //implements comparable interface
	public final Point2D name; //vertex
	public double dist = Double.MAX_VALUE; //set initial dist to infinity 
	public Vertex previous = null; 
	public final Map<Vertex, Double> neighbours = new HashMap<>();
 
	public Vertex(Point2D name) //constructor
	{
		this.name = name;
	}

	private void printPath(List<Point2D> path) //prints path and adds to list
	{
		if (this == this.previous)
		{
			System.out.print(this.name); 
			path.add(this.name); //path is list in graph
		}
		else if (this.previous == null)
		{
			System.out.print(this.name + "(unreached)");
		}
		else
		{
			path.add(this.name); //recursive call
			this.previous.printPath(path);
			System.out.print(" -> " + this.name);

		}
	}
	
	public Point2D getPoint(){ //getter
		return name;
	}
	
	public double getDist(){ //getter
		return dist;
	}
 
	public int compareTo(Vertex other) //compare distances
	{
		if (dist == other.dist)
			return name.compareTo(other.name);
 
		return Double.compare(dist, other.dist);
	}
 
	@Override public String toString()
	{
		return "(" + name + ", " + dist + ")";
	}
}
 
   public Graph(Edge[] edges) { //edges array
      graph = new HashMap<>(edges.length);
 
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1)); //checks if vertex already exists
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
 
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); 
      }
   }
 
   public void dijkstra(Point2D startName) { //dijkstra algorithm
      final Vertex source = graph.get(startName); //set source
      NavigableSet<Vertex> q = new TreeSet<>(); //create treeset
 
      for (Vertex v : graph.values()) { //init variables
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Double.MAX_VALUE;
         q.add(v); //add to q
      }
      dijkstra(q); 
   }
 
   private void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      while (!q.isEmpty()) { //for every vertex
 
         u = q.pollFirst(); // vertex with smallest dist
         if (u.dist == Double.MAX_VALUE) break; 
         
         for (Map.Entry<Vertex, Double> a : u.neighbours.entrySet()) {
            v = a.getKey(); 
 
            final double alternateDist = u.dist + a.getValue();
            if (alternateDist < v.dist) { //id dist is shorter
               q.remove(v); //remove and add
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      } 
   }
   List<Point2D> path = new ArrayList<Point2D>(); //returns all possible paths
   List<Point2D> routes = new ArrayList<Point2D>(); //returns shortest route
   
   public void printPath(Point2D endName) { //graph functions that call vertex methods
      graph.get(endName).printPath(path);
      System.out.println();
   }

   public List<Point2D> getPath(){
	   return path; //path getter
   }

   public List<Point2D> getRoutes() { //route getter
      for (Vertex v : graph.values()) {
         routes.add(v.getPoint());
      }
      return routes;
   }
}