package geometries;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import geometries.Intersectable.Box;

import static geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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

	public void kMeansBoxes(int k) {
		if (k <= 1)
			throw new IllegalArgumentException("k has to be more than 1");
		if (k > listOfShapes.size())
			k = listOfShapes.size() / 2;
		Map<Point3D, List<Intersectable>> map = new HashMap<Point3D, List<Intersectable>>();
		int i = 0;
		int jump = (int) Math.ceil(listOfShapes.size() / k);
		for (Intersectable geo : listOfShapes) {
			if (i % jump == 0)
				map.put(geo.getBox().getCenter(), new LinkedList<>());
			i++;
		}
		if (map.size() != k)
			throw new IllegalAccessError();
		boolean isLoop = true;
		while (isLoop) {
			isLoop = false;
			for (Point3D key : map.keySet())
				map.get(key).clear();
			for (Intersectable shape : listOfShapes) {
				double minDistance = Double.POSITIVE_INFINITY;
				Point3D minKey = null;
				for (Point3D key : map.keySet()) {
					double distance = shape.getBox().distance(key);
					if (distance < minDistance) {
						minDistance = distance;
						minKey = key;
					}
				}
				map.get(minKey).add(shape);
			}
			for (Point3D key : map.keySet()) {
				double sumX = 0;
				double sumY = 0;
				double sumZ = 0;
				for (Intersectable geo : map.get(key)) {
					Point3D center = geo.getBox().getCenter();
					sumX += center.getX();
					sumY += center.getY();
					sumZ += center.getZ();
				}
				int len = map.get(key).size();
				Point3D center = new Point3D(sumX / len, sumY / len, sumZ / len);
				if (!center.equals(key)) {
					isLoop = true;
					map.put(center, map.remove(key));
				}

			}
		}
		listOfShapes.clear();
		for (Point3D key : map.keySet()) {
			Geometries geometries=new Geometries();
			for(Intersectable geo:map.get(key))
				geometries.add(geo);
			listOfShapes.add(geometries);
		}

	}

}
