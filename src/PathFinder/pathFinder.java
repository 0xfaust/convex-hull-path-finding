package pathFinder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class pathFinder {
	
	public static void main(String args[]){
	
		//start execution timer
		long startTime = System.currentTimeMillis();
		
		//set canvas size
		StdDraw.setCanvasSize(1000, 1000);
		
		//check command line parameters
		Boolean verbose = false;
		System.out.println("Map Selected: "+args[0]);
		if (args.length > 1 && args[1].equals("-v")){ //|| args[1].equals("-V")) {
				System.out.println("Verbose Mode");
				verbose = true;
		}
		else {
			System.out.println("Normal Mode");
		}
		
		//import shape map
		ShapeMap map = new ShapeMap(args[0]);
	
		int hulls = 0;
		
		//array of point2d arrays of polygons 
		Point2D[][] convexHulls = new Point2D[map.amountofPolys()][];
		
		//find convex hull of every polygon on the map
		for (Polygon2D poly : map) {
			Polygon2D polygon = new Polygon2D(GrahamScan.findConvexHull(poly));
			Point2D points[] = polygon.asPointsArray();
			poly = GrahamScan.PreProcess(poly);
			StdDraw.setPenColor(Color.RED);
			poly.draw(); //draw polygons
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			polygon.drawFilled(); //draw convex hulls
			StdDraw.setPenColor(Color.GRAY);
			poly.drawFilled();
			convexHulls[hulls] = points;
			StdDraw.setPenColor(Color.RED);
			poly.draw();
			hulls++;
		}
		
		//set start and end points
		Point2D startingPoint = map.sourcePoint();
		Point2D endPoint = map.destinationPoint();
		
		//generate paths in visibility graph
		List<Graph.Edge> paths = new ArrayList<Graph.Edge>();
		paths = VisibilityGraph.generatePaths(convexHulls, startingPoint, endPoint);

		//convert list to array
		Graph.Edge[] graph = new Graph.Edge[paths.size()];
		graph = paths.toArray(graph);
		
		//run dijkstras on graph
		Graph g = new Graph(graph);		
	    g.dijkstra(startingPoint);
	    g.printPath(endPoint);
	    
	    //get the optimal route as a list
	    List<Point2D> route = new ArrayList<Point2D>();
	    route = g.getPath();

	    //print graph paths if verbose mode
		if(verbose == true){
			StdDraw.setPenColor(Color.BLACK);
			for(int i=0; i<paths.size();i++){
				 StdDraw.line(paths.get(i).getp0().getX(), paths.get(i).getp0().getY(), paths.get(i).getp1().getX(), paths.get(i).getp1().getY()); 
			}
		}
		
		//indicate start and end points
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledCircle(startingPoint.getX(), startingPoint.getY(), 0.004);
		StdDraw.filledCircle(endPoint.getX(), endPoint.getY(), 0.004);

		double totalDist = 0;
		
		//draw shortest path
		StdDraw.setPenColor(Color.RED);
		StdDraw.setPenRadius(0.010);
	    for(int i=1; i<route.size();i++){
			StdDraw.line(route.get(i-1).getX(), route.get(i-1).getY(), route.get(i).getX(), route.get(i).getY());
			totalDist += VisibilityGraph.dist(route.get(i-1), route.get(i));
		}

	    //stop timer and display execution time
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Execution time (ms) = " + totalTime);
		System.out.println("Length of path found = " + totalDist);
	}	
}
