/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 * An interface of all geometries that are intersectable by ray
 *
 */
public interface Intersectable {
	/**
	 * This function checks if and where a ray intersect a geometry
	 * @param ray- the ray that intersect the geometry
	 * @return a list of the intersections points, or null if there are no intersections points
	 */
	List<Point3D> findIntersections(Ray ray);


}
