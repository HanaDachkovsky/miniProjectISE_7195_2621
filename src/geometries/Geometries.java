package geometries;



import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;


/**
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai Geometries is a collection of
 *         geometries.
 *
 */
public class Geometries extends Intersectable {
	private List<Intersectable> listOfShapes;

	/**
	 * Default ctor
	 */
	public Geometries() {
		/// we chose LinkedList because we don't have to search
		// or to delete from geometries
		// and the time of adding to LinkedList is O(1)
		listOfShapes = new LinkedList<>();
		box = new Box(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
				Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
	}

	/**
	 * 
	 * @param geometries- the geometries of the list
	 */
	public Geometries(Intersectable... geometries) {
		listOfShapes = new LinkedList<Intersectable>(Arrays.asList(geometries));
		box = new Box(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
				Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		for (Intersectable shape : geometries) {
			box.resize(shape.getBox());
		}
	}

	/**
	 * 
	 * @param geometries-the geometries to add
	 */

	public void add(Intersectable... geometries) {
		List<Intersectable> list = Arrays.asList(geometries);
		listOfShapes.addAll(list);
		for (Intersectable shape : geometries) {
			box.resize(shape.getBox());
		}
	}

//	@Override
//	public List<GeoPoint> findGeoIntersections(Ray ray) {
//		LinkedList<GeoPoint> allInter = null;//united list of intersections
//		for (Intersectable shape : listOfShapes) {
//			List<GeoPoint> shapeInter = shape.findGeoIntersections(ray);//intersections for each shape
//			if (shapeInter != null) {//add to the united list
//				if (allInter == null)
//					allInter = new LinkedList<GeoPoint>();
//				for (GeoPoint point : shapeInter) {
//					allInter.add(point);
//				}
//			}
//		}
//		return allInter;
//	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		LinkedList<GeoPoint> allInter = null;// united list of intersections
		for (Intersectable shape : listOfShapes) {
			List<GeoPoint> shapeInter = shape.findGeoIntersections(ray, maxDistance);// intersections for each shape
			if (shapeInter != null) {// add to the united list
				if (allInter == null)
					allInter = new LinkedList<GeoPoint>();
				for (GeoPoint point : shapeInter) {
					allInter.add(point);
				}
			}
		}
		return allInter;
	}

	/**
	 * Divides the geometries to groups by distance by algorithm k means
	 * @param k the number of groups
	 */
	public void kMeansBoxes(int k)  {
		if (k <= 1)
			throw new IllegalArgumentException("k has to be more than 1");
		if (k > listOfShapes.size())// k has to be less than number of shapes
			k = listOfShapes.size() / 2;
		List<Point3D> centers = new ArrayList<>();// array of centers for each group of shapes
		List<ArrayList<Intersectable>> listOfLists = new ArrayList<ArrayList<Intersectable>>();// list of all groups
		int i = 0;// index of the shape in
		int counter = 0;// count from 0 to k centers
		int jump = (int) (listOfShapes.size() / k);// jump for "random" choice of k centers
		for (Intersectable geo : listOfShapes) {// "random" choice of k centers
			if (i % jump == 0 && counter < k) {
				counter++;
				centers.add(geo.getBox().getCenter());
				listOfLists.add(new ArrayList<Intersectable>());// create new list for this group
			}
			i++;
		}
		if (listOfLists.size() != k) 
			throw new ArithmeticException("must k groups");
		
		boolean isLoop = true;
		while (isLoop) {// run while at least 1 center has changed
			isLoop = false;
			for (var list : listOfLists)
				list.clear();
			for (Intersectable shape : listOfShapes) {// for each geo find the minimal distance and put in the group
				double minDistance = Double.POSITIVE_INFINITY;
				Point3D minCenter = null;
				for (Point3D cen : centers) {
					double distance = shape.getBox().distance(cen);
					if (distance < minDistance) {
						minDistance = distance;
						minCenter = cen;
					}
				}
				listOfLists.get(centers.indexOf(minCenter)).add(shape);// add to group
			}
			for (var list : listOfLists) {// recalculate the centers of each group
				double sumX = 0;
				double sumY = 0;
				double sumZ = 0;
				for (Intersectable geo : list) {
					Point3D center = geo.getBox().getCenter();
					sumX += center.getX();
					sumY += center.getY();
					sumZ += center.getZ();
				}
				int len = list.size();
				Point3D center = new Point3D(sumX / len, sumY / len, sumZ / len);
				if (!center.equals(centers.get(listOfLists.indexOf(list)))) {// if the center has changed-replace
					isLoop = true;// run until no center changes
					centers.set(listOfLists.indexOf(list), center);// replace the center with the new calculated center
				}

			}
		}
		listOfShapes.clear();
		for (var list : listOfLists) {// make k geometries
			Geometries geometries = new Geometries();
			for (Intersectable geo : list)
				geometries.add(geo);
			listOfShapes.add(geometries);
		}

	}

}
