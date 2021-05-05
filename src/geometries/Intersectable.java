/**
 * 
 */
package geometries;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Equals;

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
	List<GeoPoint> findGeoIntersections(Ray ray);
	/**
	 * 
	 * @author Hana Dachkovsky and Sara Tamar Amitai
	 *point that contains the geometry it is on it
	 */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point3D point;
	    /**
	     * ctor that gets 2 parameters
	     * @param geometry- the shape
	     * @param point- a point on the shape
	     */
	    public GeoPoint(Geometry geometry, Point3D point) {
	    	this.geometry=geometry;
	    	this.point=point;
	    }
	    @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other= (GeoPoint)obj;
			return geometry.equals(other.geometry)&& point.equals(other.point);
		}
	}
}
