package geometries;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 * Geometries is a collection of geometries.
 *
 */
public class Geometries implements Intersectable {
	private List<Intersectable> listOfShapes;

	/**
	 *  Default ctor 
	 */
	public Geometries() {
		///we chose LinkedList because we don't have to search
		//or to delete from geometries
		//and the time of adding to LinkedList is O(1)
		listOfShapes = new LinkedList<>();
	}

	/**
	 * 
	 * @param geometries- the geometries of the list
	 */
	public Geometries(Intersectable... geometries) {
		listOfShapes = new LinkedList<Intersectable>(Arrays.asList(geometries));
	}
	
	/**
	 * 
	 * @param geometries-the geometries to add
	 */

	public void add(Intersectable... geometries) {
		List<Intersectable> list = Arrays.asList(geometries);
		listOfShapes.addAll(list);
	}

	
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		LinkedList<GeoPoint> allInter = null;//united list of intersections
		for (Intersectable shape : listOfShapes) {
			List<GeoPoint> shapeInter = shape.findGeoIntersections(ray);//intersections for each shape
			if (shapeInter != null) {//add to the united list
				if (allInter == null)
					allInter = new LinkedList<GeoPoint>();
				for (GeoPoint point : shapeInter) {
					allInter.add(point);
				}
			}
		}
		return allInter;
	}

}
